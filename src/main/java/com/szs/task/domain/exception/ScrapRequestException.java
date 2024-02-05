package com.szs.task.domain.exception;

import com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO;
import lombok.AllArgsConstructor;

import lombok.Getter;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.net.URI;

@Getter
@AllArgsConstructor
public class ScrapRequestException extends RuntimeException {
    private HttpResponseStatusCodeNameVO errorCode;
    private int code;
    private String message;

    public ScrapRequestException(HttpResponseStatusCodeNameVO errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.errorCode = errorCode;
    }
}
