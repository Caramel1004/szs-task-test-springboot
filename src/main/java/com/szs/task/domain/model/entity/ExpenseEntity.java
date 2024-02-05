package com.szs.task.domain.model.entity;

import com.szs.task.domain.model.dto.income.ExpenseModelDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "expense")
public class ExpenseEntity {
    /*
    insurance_premium	DECIMAL(15, 2)	보험료 납입 금액
    medical_expenses	DECIMAL(15, 2)	의료비 납입 금액
    education_expenses	DECIMAL(15, 2)	교육비 납입 금액
    donation_amount	DECIMAL(15, 2)	기부금 납입 금액
    retirement_fund	DECIMAL(15, 2)	퇴직금 납입 금액
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_Id")
    private Long expenseId;

    @Column(name = "insurance_premium", nullable = false)
    private BigDecimal insurancePremium;

    @Column(name = "medical_expenses", nullable = false)
    private BigDecimal medicalExpenses;

    @Column(name = "education_expenses", nullable = false)
    private BigDecimal educationExpenses;

    @Column(name = "donation_amount", nullable = false)
    private BigDecimal donationAmount;

    @Column(name = "retirement_fund", nullable = false)
    private BigDecimal retirementFund;

    public ExpenseEntity toEntity (ExpenseModelDTO dto) {
        return ExpenseEntity.builder()
                .insurancePremium(dto.getInsurancePremium())
                .medicalExpenses(dto.getMedicalExpenses())
                .educationExpenses(dto.getEducationExpenses())
                .donationAmount(dto.getDonationAmount())
                .retirementFund(dto.getRetirementFund())
                .build();
    }
}
