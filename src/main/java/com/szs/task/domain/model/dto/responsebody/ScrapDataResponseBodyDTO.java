package com.szs.task.domain.model.dto.responsebody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.szs.task.domain.model.dto.income.DeterminedTaxModelDTO;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.model.vo.HttpResponseModelVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "SUCCESS_GET_SCRAP_DATA: 스크랩 데이터 Response Body")
public class ScrapDataResponseBodyDTO {

    @Schema(name = "result", description = "HTTP 상태 응답 모델")
    private HttpResponseModelVO result;

    @Schema(name = "data", description = "소득, 세액, 납부 데이터")
    @JsonProperty("소득정보")
    private IncomeTaxModelDTO data;

    public ScrapDataResponseBodyDTO(IncomeTaxModelDTO dto, HttpResponseModelVO vo) {
        this.result = vo;
        this.data = dto;
    }
}
