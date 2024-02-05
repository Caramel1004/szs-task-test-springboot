package com.szs.task.domain.controller;

import com.szs.task.domain.model.dto.responsebody.HttpResponseModelDTO;
import com.szs.task.domain.model.dto.responsebody.UserAuthResponseBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import com.szs.task.domain.model.dto.requestbody.UserLoginRequestBodyDTO;
import com.szs.task.domain.model.vo.HttpResponseModelVO;
import com.szs.task.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.SUCCESS_MEMBER_LOGIN;
import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.SUCCESS_MEMBER_SIGNUP;

@Tag(name = "인증/인가, 가입")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/szs")
public class UserController {

    private final UserService userService;

    /**
     * 1. 회원 가입
     *
     * @Requestbody UserDTO
     * @Property String userId: 유저아이디
     * @Property String password: 비밀번호
     * @Property String name: 성명
     * @Property String regNo: 주민등록번호
     **/

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "회원가입 실패(중복된 유저)")
    })
    public ResponseEntity<HttpResponseModelDTO> signup(@RequestBody @Valid UserModelDTO userDTO) {
        // 1. 유저 저장
        userService.saveUser(userDTO);

        // Response 처리
        HttpResponseModelDTO responseBody = new HttpResponseModelDTO(SUCCESS_MEMBER_SIGNUP);
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }


    /**
     * 2. 로그인
     *
     * @Requestbody UserDTO
     * @Property String userId: 유저아이디
     * @Property String password: 비밀번호
     **/
    @Operation(description = "인증 토큰을 받습니다.", summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<UserAuthResponseBodyDTO> login(@RequestBody @Valid UserLoginRequestBodyDTO userLoginRequestBodyDTO) {
        // 1. 인증 토큰 발급
        String accessToken = userService.getAuthToken(userLoginRequestBodyDTO);

        // Response 처리
        UserAuthResponseBodyDTO responseBody = new UserAuthResponseBodyDTO(new HttpResponseModelVO(SUCCESS_MEMBER_LOGIN), accessToken);
        return ResponseEntity.status(responseBody.getResult().getCode()).body(responseBody);
    }
}
