package com.szs.task.domain.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpResponseStatusCodeNameVO {

    /**
     * 회원 관련 성공 상태 코드: 2xx
     */
    SUCCESS_MEMBER_SIGNUP(HttpStatus.CREATED.value(), "회원 가입 성공"),
    SUCCESS_MEMBER_LOGIN(HttpStatus.OK.value(), "로그인 성공"),


    /**
     * 스크랩핑, 결정세액조회 관련 성공 상태 코드: 2xx
     **/
    SUCCESS_GET_SCRAP_DATA(HttpStatus.OK.value(), "회원님이 요청하신 소득 정보를 가져왔습니다."),
    SUCCESS_GET_DETERMINED_TAX(HttpStatus.OK.value(), "회원님의 결정 세액 정보입니다."),


    /**
     * 요청 바디 invalid 에러 코드: 4xx
     **/
    BAD_REQUEST_BODY_INVALID_ERROR(HttpStatus.BAD_REQUEST.value(), "잘못된 요청 바디 입니다."),


    /**
     * 스크랩핑, 회원 관련 에러 코드: 4xx
     **/
    BAD_NOT_MATCH_REGNO_AND_NAME(HttpStatus.BAD_REQUEST.value(), "해당 고객님은 주민등록상에 존재하지 않습니다."),
    BAD_DUPLICATE_USER_INVALID_ERROR(HttpStatus.BAD_REQUEST.value(), "중복된 유저가 존재합니다."),
    BAD_NOT_MATCH_MEMBER(HttpStatus.BAD_REQUEST.value(), "일치하는 회원이 없습니다."),


    /**
     * 인증 토큰 관련 에러 코드: 4xx
     **/
    BAD_REQUEST_HEADER(HttpStatus.BAD_REQUEST.value(), "잘못된 요청 헤더 입니다."),
    UNAUTHORIZED_PAYLOAD_MEMBER_DATA(HttpStatus.UNAUTHORIZED.value(), "토큰에 저장된 유저 정보가 잘못되었습니다."),
    UNAUTHORIZED_EXPIRED_JWT(HttpStatus.UNAUTHORIZED.value(), "만료된 토큰 입니다."),
    UNAUTHORIZED_MALFORMED_JWT(HttpStatus.UNAUTHORIZED.value(), "잘못된 접근 토큰 입니다."),
    UNAUTHORIZED_NOT_EXIST_JWT(HttpStatus.UNAUTHORIZED.value(), "인증 토큰이 없습니다."),


    /**
     * 서버 에러: 5xx
     **/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 에러"),
    INTERNAL_SERVER_DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "잘못된 DB 접근 방식 입니다."),
    SZS_SCRAP_DATA_REQUEST_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "스크랩핑 외부 API 요청 ERROR");


    @Schema(description = "상태 코드")
    private final int code;

    @Schema(description = "상태 메세지")
    private final String message;
}
