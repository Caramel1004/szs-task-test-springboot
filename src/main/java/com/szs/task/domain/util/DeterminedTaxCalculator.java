package com.szs.task.domain.util;

import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


@Slf4j
public final class DeterminedTaxCalculator {

    public static String calculatorTaxAndFormatToKRW(IncomeTaxModelDTO scrapData) {
        BigDecimal earnedIncome = scrapData.getEarnedIncome();
        BigDecimal calculatedTax = scrapData.getCalculatedTax();

        BigDecimal insurancePremium = scrapData.getExpense().getInsurancePremium();
        BigDecimal medicalExpense = scrapData.getExpense().getMedicalExpenses();
        BigDecimal educationExpense = scrapData.getExpense().getEducationExpenses();
        BigDecimal donationAmount = scrapData.getExpense().getDonationAmount();
        BigDecimal retirementFund = scrapData.getExpense().getRetirementFund();

        // 근로소득세액공제금액
        BigDecimal earnedIncomeTaxDeduction = calculatedTax.multiply(new BigDecimal("0.55"));
        log.info("earnedIncomeTaxDeduction, 근로소득세액공제금액: " + earnedIncomeTaxDeduction);

        // 보험료공제금액
        BigDecimal insurancePremiumDeduction = insurancePremium.multiply(new BigDecimal("0.12"));
        log.info("insurancePremiumDeduction, 보험료공제금액: " + insurancePremiumDeduction);

        // 의료비공제금액
        BigDecimal ss = earnedIncome.multiply(new BigDecimal("0.03"));
        BigDecimal medicalExpenseTT = medicalExpense.subtract(ss);
        BigDecimal medicalExpenseDeduction = medicalExpenseTT.multiply(new BigDecimal("0.15"));
        log.info("조건 전 medicalExpenseDeduction, 의료비공제금액: " + medicalExpenseDeduction);
        medicalExpenseDeduction = medicalExpenseDeduction.compareTo(new BigDecimal(0)) >= 0? medicalExpenseDeduction : new BigDecimal(0);
        log.info("조건 후 medicalExpenseDeduction, 의료비공제금액: " + medicalExpenseDeduction);

        // 교육비공제금액
        BigDecimal educationExpenseDeduction = educationExpense.multiply(new BigDecimal("0.15"));
        log.info("educationExpenseDeduction, 교육비공제금액: " + educationExpenseDeduction);

        // 기부금공제금액
        BigDecimal donationAmountDeducton = donationAmount.multiply(new BigDecimal("0.15"));
        log.info("donationAmountDeducton, 기부금공제금액: " + donationAmountDeducton);

        // 퇴직연금세액공제금액
        BigDecimal retirementFundDeduction = retirementFund.multiply(new BigDecimal("0.15"));
        log.info("retirementFundDeduction, 퇴직연금세액공제금액: " + retirementFundDeduction);

        // 특별세액공제금액
        List<BigDecimal> numbers = Arrays.asList(
                insurancePremiumDeduction,
                medicalExpenseDeduction,
                educationExpenseDeduction,
                donationAmountDeducton
        );

        BigDecimal specialTaxDeduction = numbers.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("specialTaxDeduction, 특별세액공제금액: " + specialTaxDeduction);

        // 표준세액공제금액
        BigDecimal standardTaxDeduction = specialTaxDeduction.compareTo(new BigDecimal("130000")) < 0? new BigDecimal("130000") : new BigDecimal("0");
        log.info("standardTaxDeduction, 표준세액공제금액: " + standardTaxDeduction);

        BigDecimal determinedTax = calculatedTax.subtract(
                Arrays.asList(
                        earnedIncomeTaxDeduction,
                        specialTaxDeduction,
                        standardTaxDeduction,
                        retirementFundDeduction
                ).stream().reduce(BigDecimal.ZERO, BigDecimal::subtract)
        );

        log.info("조건 전 determinedTax, 결정세액공제금액: " + determinedTax);
        determinedTax = determinedTax.compareTo(new BigDecimal(0)) >= 0? determinedTax : new BigDecimal(0);
        log.info("조건 후 determinedTax, 결정세액공제금액: " + determinedTax);

        DecimalFormat decimalFormat = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.KOREA));
//        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.KOREA);
        decimalFormat.setCurrency(Currency.getInstance("KRW"));

        return decimalFormat.format(determinedTax);
    }
}
