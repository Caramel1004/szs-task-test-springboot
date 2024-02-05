package com.szs.task.domain.model.vo;

import com.szs.task.domain.exception.dto.ErrorResponseBodyDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "HTTP 응답 상태 모델")
public class HttpResponseModelVO {

    @Schema(description = "상태 코드")
    private int code;

    @Schema(description = "상태 메세지")
    private String message;

    @Schema(description = "코드 네임")
    private HttpResponseStatusCodeNameVO codeName;

    public HttpResponseModelVO(HttpResponseStatusCodeNameVO vo) {
        this.code = vo.getCode();
        this.message = vo.getMessage();
        this.codeName = vo;
    }

    public HttpResponseModelVO(ErrorResponseBodyDTO dto) {
        this.code = dto.getCode();
        this.message = dto.getMessage();
        this.codeName = dto.getCodeName();
    }
}
