package com.szs.task.domain.exception.handler;

import com.szs.task.domain.exception.*;
import com.szs.task.domain.exception.dto.ErrorResponseBodyDTO;
import com.szs.task.domain.model.dto.responsebody.HttpResponseModelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.BAD_REQUEST_BODY_INVALID_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Exception InvalidDatabaseAccessException: 데이터 베이스 관련 에러
     **/
    @ExceptionHandler(InvalidDatabaseAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseModelDTO> invalidDatabaseAccessException(InvalidDatabaseAccessException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }

    /**
     * @Exception MethodArgumentNotValidException: Invalid 요청 바디
     **/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HashMap<String, Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        HashMap<String, Object> responseBody = new HashMap<String, Object>();

        // HTTP 상태
        responseBody.put("result", new ErrorResponseBodyDTO(BAD_REQUEST_BODY_INVALID_ERROR));

        // Validation
        e.getBindingResult().getAllErrors()
                .forEach(c -> responseBody.put(((FieldError) c).getField(), c.getDefaultMessage()));

        return ResponseEntity.status(e.getStatusCode()).body(responseBody);
    }

    /**
     * @Exception NotMatchRegNoException: 유저의 주민등록번호가 일치하지 읺을 경우
     **/
    @ExceptionHandler(NotMatchRegNoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseModelDTO> inValidPatternTypeExceptionHandler(NotMatchRegNoException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }

    /**
     * @Exception DuplicateUserException: 중복 유저 존재하는 경우
     **/
    @ExceptionHandler(DuplicateUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseModelDTO> inValidPatternTypeExceptionHandler(DuplicateUserException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }

    /**
     * @Exception NotFoundUserException: 유저를 조호하지 못한 경우
     **/
    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseModelDTO> notFoundUserException(NotFoundUserException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }

    /**
     * @Exception UnAuthorizationException: 유효하지 못한 인증
     **/
    @ExceptionHandler(UnAuthorizationJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<HttpResponseModelDTO> unAuthorizationException(UnAuthorizationJwtException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }

    /**
     * @Exception ScrapRequestException: 스크랩핑 데이터가 없는 경우 OR 외부 API 서버 에러
     **/
    @ExceptionHandler(ScrapRequestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpResponseModelDTO> scrapRequestException(ScrapRequestException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }

    @ExceptionHandler(InValidRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponseModelDTO> inValidRequestHeaderException(InValidRequestHeaderException e) {
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(new ErrorResponseBodyDTO(e));
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }
}
