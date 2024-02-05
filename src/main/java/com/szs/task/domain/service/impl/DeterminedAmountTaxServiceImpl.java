package com.szs.task.domain.service.impl;

import com.szs.task.domain.dao.DeterminedAmountTaxDAO;
import com.szs.task.domain.dao.UserDAO;
import com.szs.task.domain.exception.NotMatchRegNoException;
import com.szs.task.domain.exception.UnAuthorizationJwtException;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.model.dto.income.DeterminedTaxModelDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import com.szs.task.domain.service.DeterminedAmountTaxService;
import com.szs.task.domain.util.DeterminedTaxCalculator;
import com.szs.task.domain.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.Locale;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.*;

@Service
@RequiredArgsConstructor
public class DeterminedAmountTaxServiceImpl implements DeterminedAmountTaxService {

    private final DeterminedAmountTaxDAO determinedAmountTaxDAO;
    private final UserDAO userDAO;

    @Override
    public DeterminedTaxModelDTO getDeterminedAmountTaxOfUser(String accessToken) {
        boolean isExpiredJWT = JwtUtil.isExpiredJWT(accessToken);

        /*
         * Exception UnAuthorizationJwtException: 만료된 토큰
         */
        if (isExpiredJWT) {
            throw new UnAuthorizationJwtException(UNAUTHORIZED_EXPIRED_JWT);
        }

        String userId = JwtUtil.parseAccessToken(accessToken);

        boolean isExistingUser = userDAO.isExistingUserId(userId);

        /*
         * Exception NotMatchRegNoException: 유효하지않는 유저아이디
         */
        if(!isExistingUser) {
            throw new NotMatchRegNoException(BAD_NOT_MATCH_MEMBER);
        }


        // 1. userId와 일치하는 멤버 조회
        UserModelDTO user = userDAO.getUserInfoByUserId(userId);

        // 2. 주민등록번호와 일치하는 스크랩 데이터를 조회
        IncomeTaxModelDTO scrapData =  determinedAmountTaxDAO.getIncomeTaxByRegNo(user.getRegNo());

        // 3. 결정세액 계산
        String determinedTaxFormat = DeterminedTaxCalculator.calculatorTaxAndFormatToKRW(scrapData);

        // 4. 퇴직 연금 세액 공제액
        BigDecimal retirementFundDeduction = scrapData.getExpense().getRetirementFund().multiply(new BigDecimal("0.15"));
        DecimalFormat decimalFormat = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.KOREA)); // ex) 🇰🇷 원화 6,000,000 패턴
        decimalFormat.setCurrency(Currency.getInstance("KRW")); //ex) 원화 심볼 삭제
        String retirementFundDeductionFormat = decimalFormat.format(retirementFundDeduction);
        System.out.println(retirementFundDeduction);

        DeterminedTaxModelDTO dto = new DeterminedTaxModelDTO(user.getName(), determinedTaxFormat, retirementFundDeductionFormat);

        return dto;
    }
}
