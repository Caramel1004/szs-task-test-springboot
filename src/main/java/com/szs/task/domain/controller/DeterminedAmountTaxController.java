package com.szs.task.domain.controller;

import com.szs.task.domain.exception.NotFoundUserException;
import com.szs.task.domain.model.dto.income.DeterminedTaxModelDTO;
import com.szs.task.domain.model.dto.responsebody.DeterminedTaxResponseBodyDTO;
import com.szs.task.domain.model.vo.HttpResponseModelVO;
import com.szs.task.domain.service.DeterminedAmountTaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.SUCCESS_GET_DETERMINED_TAX;
import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.UNAUTHORIZED_PAYLOAD_MEMBER_DATA;

@Tag(name = "결정세액조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/szs")
public class DeterminedAmountTaxController {

    private final DeterminedAmountTaxService determinedAmountTaxService;

    /**
     * 4. 회원의 결정 세액 정보 조회
     *
     * @RequestHeader Authorization: jwt
     * @JWT userId: 유저 아이디
     **/
    @Operation(summary = "결정세액 정보 조회")
    @GetMapping("/refund")
    public ResponseEntity<DeterminedTaxResponseBodyDTO> getDeterminedAmountTaxOfUser(@RequestHeader("Authorization") String authHeader) {

        String accessToken = authHeader.split("Bearer ")[1];

        DeterminedTaxModelDTO determinedTaxModelDTO = determinedAmountTaxService.getDeterminedAmountTaxOfUser(accessToken);

        // Response 처리
        DeterminedTaxResponseBodyDTO responseBody = new DeterminedTaxResponseBodyDTO(
                determinedTaxModelDTO,
                new HttpResponseModelVO(SUCCESS_GET_DETERMINED_TAX)
        );

        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }
}
