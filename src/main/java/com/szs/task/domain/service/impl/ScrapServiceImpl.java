package com.szs.task.domain.service.impl;


import com.szs.task.domain.dao.ScrapDAO;
import com.szs.task.domain.dao.UserDAO;
import com.szs.task.domain.exception.NotFoundUserException;
import com.szs.task.domain.exception.NotMatchRegNoException;
import com.szs.task.domain.exception.UnAuthorizationJwtException;
import com.szs.task.domain.model.dto.income.ExpenseModelDTO;
import com.szs.task.domain.model.dto.income.ExternalScrapResponseDTO;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import com.szs.task.domain.service.ScrapService;
import com.szs.task.domain.service.WebClientService;
import com.szs.task.domain.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.HashMap;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScrapServiceImpl implements ScrapService {

    private final WebClientService webClientService;
    private final ScrapDAO scrapDAO;
    private final UserDAO userDAO;

    @Override
    public IncomeTaxModelDTO saveScrapDataOfUser(UserScrapRequestBodyDTO dto, String accessToken) throws WebClientRequestException {
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
            throw new NotMatchRegNoException(BAD_NOT_MATCH_REGNO_AND_NAME);
        }

        boolean isMatchingUser = userDAO.getUserInfoByRegNoAndName(dto);

        /*
         * Exception NotMatchRegNoException: 주민등록번호 이름으로 매칭된 유저가 없을 경우
         */
        if(!isMatchingUser) {
            throw new NotMatchRegNoException(BAD_NOT_MATCH_REGNO_AND_NAME);
        }

        // 1. 스크랩핑 외부 API 요청
        ExternalScrapResponseDTO scrapData = webClientService.getScrapData(dto);

        /*
         * Exception NotMatchRegNoException: 주민등록번호 이름 일치하지 않을 경우 == 스크랩 정보가 없는 경우
         */
        if (!scrapData.getStatus().equalsIgnoreCase("success")) {
            throw new NotMatchRegNoException(BAD_NOT_MATCH_REGNO_AND_NAME);
        }

        ExpenseModelDTO expenseDTO = new ExpenseModelDTO(scrapData); // 특별세액공제금액 계산할 데이터 모델
        IncomeTaxModelDTO incomeTaxModelDTO = new IncomeTaxModelDTO(scrapData, expenseDTO); // 근로 소득, 세액 데이터 모델

        /*
         * 2. 스크랩핑 데이터 저장
         * @Param IncomeTaxDTO : 근로 소득(연간 합산), 세액 데이터 모델
         * @Param ExpenseDTO:  특별세액공제금액,퇴직연금세액공제금액 계산할 데이터 모델(보험료, 교육비, 기부금, 의료비, 퇴직연금)
         * */
        scrapDAO.saveScrapDataOfUser(incomeTaxModelDTO, expenseDTO);

        return incomeTaxModelDTO;
    }
}
