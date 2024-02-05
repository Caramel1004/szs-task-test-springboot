package com.szs.task.domain.service.impl;

import com.szs.task.domain.dao.UserDAO;
import com.szs.task.domain.exception.DuplicateUserException;
import com.szs.task.domain.exception.NotFoundUserException;
import com.szs.task.domain.exception.NotMatchRegNoException;
import com.szs.task.domain.model.dto.requestbody.UserLoginRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import com.szs.task.domain.service.UserService;
import com.szs.task.domain.service.WebClientService;
import com.szs.task.domain.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final WebClientService webClientService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;

    @Override
    public void saveUser(UserModelDTO userDTO) {

        // 1. 회원의 인적 정보 조회(유효한 인적 주민등록번호 인지 조회) => 스크래핑으로 조회되지 않으면 NotMatchRegNoException
        boolean isMatchedUserNameAndRegNo = webClientService.isMatchedUserNameAndRegNo(userDTO);

        /*
         * Exception NotMatchRegNoException: 주민등록번호 이름 일치하지 않을 경우 == 스크랩 정보가 없는 경우
         */
        if(!isMatchedUserNameAndRegNo) {
            throw new NotMatchRegNoException(BAD_NOT_MATCH_REGNO_AND_NAME);
        }

        // 2. 회원 중복 조회 (유저아이디 or 이름 or 주민등록번호 하나라도 있으면 중복)
        boolean isExistingUserId = userDAO.isExistingUserId(userDTO.getUserId());

        /*
         * Exception DuplicateUserException: 중복 유저아이디 있는 경우
         */
        if (isExistingUserId) {
            throw new DuplicateUserException(BAD_DUPLICATE_USER_INVALID_ERROR);
        }

        // 4. 주민등록번호 암호화 미구현 , 비밀번호 암호화 구현
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
//        userDTO.setPassword(encodedPassword);


        // 3. 회원 가입
        userDAO.saveUser(userDTO);
    }


    @Override
    public String getAuthToken(UserLoginRequestBodyDTO reqDTO) {

        boolean isExistingUser = userDAO.isExistingUser(reqDTO);

        if(!isExistingUser) {
            throw new NotFoundUserException(BAD_NOT_MATCH_MEMBER);
        }

        return JwtUtil.createAuthorizationToken(reqDTO.getUserId(), 60 * 60 * 1000);

        //security 버전
//        UsernamePasswordAuthenticationToken accessToken
//                = new UsernamePasswordAuthenticationToken(reqDTO.getUserId(), reqDTO.getPassword());

//        // 토큰에 요청정보 등록
//        accessToken.setDetails(new WebAuthenticationDetails(servletReq));
//
//        // 토큰을 이용하여 인증 요청 -> 로그인
//        Authentication authentication = authenticationManager.authenticate(accessToken);
//
//        log.info("인증여부 : " + authentication.isAuthenticated());
//
//        User authUser  = (User) authentication.getPrincipal();
//
//        log.info("인증된 사용자 아이디 : " + authUser.getUsername());
//
//        // Security Context에 인증 사용자 등록
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
