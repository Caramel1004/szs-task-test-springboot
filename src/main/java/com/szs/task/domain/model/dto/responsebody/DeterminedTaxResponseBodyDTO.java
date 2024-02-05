package com.szs.task.domain.model.dto.responsebody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.szs.task.domain.model.dto.income.DeterminedTaxModelDTO;
import com.szs.task.domain.model.vo.HttpResponseModelVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "SUCCESS_GET_DETERMINED_TAX: 스크랩 데이터 Response Body")
public class DeterminedTaxResponseBodyDTO {

    @Schema(name = "result", description = "HTTP 상태 응답")
    private HttpResponseModelVO result;

    @Schema(name="data", description = "data")
    private DeterminedTaxModelDTO data;


    public DeterminedTaxResponseBodyDTO() {

    }

    public DeterminedTaxResponseBodyDTO(DeterminedTaxModelDTO dto, HttpResponseModelVO vo) {
        this.result = vo;
        this.data = dto;
    }
}
