package com.szs.task.domain.controller;

import com.szs.task.domain.exception.ScrapRequestException;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.model.dto.responsebody.ScrapDataResponseBodyDTO;
import com.szs.task.domain.model.vo.HttpResponseModelVO;
import com.szs.task.domain.service.ScrapService;
import io.netty.handler.codec.http.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.*;

@Tag(name = "스크랩핑")
@RestController
@RequestMapping(value = "/szs")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    /**
     * 3. 스크랩핑
     *
     * @RequestHeader Authorization: jwt
     * @Requestbody UserScrapRequestBodyDTO: 회원의 스크랩핑 요청바디
     * @Property String name: 성명
     * @Property String regNo: 주민등록번호
     **/
    @Operation(summary = "스크랩핑 데이터 저장")
    @PostMapping("/scrap")
    public ResponseEntity<ScrapDataResponseBodyDTO> saveScrapDataOfUser(@RequestBody @Valid UserScrapRequestBodyDTO dto,
                                                                        @RequestHeader("Authorization") String authHeader ) throws WebClientRequestException {
        try {
            String accessToken = authHeader.split("Bearer ")[1];

            IncomeTaxModelDTO incomeTaxModelDTO = scrapService.saveScrapDataOfUser(dto, accessToken);

            // Response 처리
            ScrapDataResponseBodyDTO responseBody = new ScrapDataResponseBodyDTO(incomeTaxModelDTO, new HttpResponseModelVO(SUCCESS_GET_SCRAP_DATA));
            return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
        } catch (WebClientRequestException e) {
            throw new ScrapRequestException(SZS_SCRAP_DATA_REQUEST_ERROR);
        }
    }
}
