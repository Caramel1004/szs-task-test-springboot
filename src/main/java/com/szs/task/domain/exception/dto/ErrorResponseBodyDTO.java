package com.szs.task.domain.exception.dto;

import com.szs.task.domain.exception.*;
import com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Schema(description = "4xx, 5xx 에러 응답 데이터")
public class ErrorResponseBodyDTO {

    @Schema(description = "코드 네임")
    private HttpResponseStatusCodeNameVO codeName;

    @Schema(description = "상태 코드")
    private int code;

    @Schema(description = "상태 메세지")
    private String message;


    // 요청 바디 값 안맞는 경우
    public ErrorResponseBodyDTO(HttpResponseStatusCodeNameVO codeName) {
        this.codeName = codeName;
        this.code = codeName.getCode();
        this.message = codeName.getMessage();
    }


    public ErrorResponseBodyDTO(UnAuthorizationJwtException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponseBodyDTO(NotFoundUserException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponseBodyDTO(DuplicateUserException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponseBodyDTO(InvalidDatabaseAccessException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponseBodyDTO(NotMatchRegNoException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponseBodyDTO(ScrapRequestException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }

    public ErrorResponseBodyDTO(InValidRequestHeaderException e) {
        this.codeName = e.getErrorCode();
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}
