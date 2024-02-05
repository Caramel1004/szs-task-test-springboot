package com.szs.task.domain.model.dto.responsebody;

import com.szs.task.domain.exception.dto.ErrorResponseBodyDTO;
import com.szs.task.domain.model.vo.HttpResponseModelVO;
import com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class HttpResponseModelDTO {

    @Schema(name = "result", description = "HTTP 상태 응답")
    private HttpResponseModelVO result;

    public HttpResponseModelDTO(HttpResponseStatusCodeNameVO vo) {
        this.result = new HttpResponseModelVO(vo);
    }

    public HttpResponseModelDTO(ErrorResponseBodyDTO dto) {
        this.result = new HttpResponseModelVO(dto);
    }
}
