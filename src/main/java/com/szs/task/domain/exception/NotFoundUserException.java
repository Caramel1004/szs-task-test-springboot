package com.szs.task.domain.exception;

import com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundUserException extends RuntimeException {

    private HttpResponseStatusCodeNameVO errorCode;
    private int code;
    private String message;

    public NotFoundUserException(HttpResponseStatusCodeNameVO errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode;
    }
}
