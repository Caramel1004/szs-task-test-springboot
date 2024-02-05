//package com.szs.task.domain.security.jwt.filter;
//
//import com.szs.task.domain.exception.NotFoundUserException;
//import com.szs.task.domain.model.dto.requestbody.UserLoginRequestBodyDTO;
//import com.szs.task.domain.security.jwt.dto.SecurityUserDetailDTO;
//import com.szs.task.domain.security.jwt.constants.JwtConstants;
////import com.szs.task.domain.security.jwt.provider.JwtTokenProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.AuthenticationException;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.io.IOException;
//
//import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.BAD_NOT_MATCH_MEMBER;
//
//
//@Slf4j
//@Component
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//
//    @Autowired
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        super.setAuthenticationManager(authenticationManager);
//
//        log.info("authenticationManager" + authenticationManager);
//        setFilterProcessesUrl(JwtConstants.AUTH_LOGIN_END_POINT);
//    }
//
//    /**
//     * 🔐 인증 시도 메소드
//     * /login 엔드포인트로 요청 => 필터 => 인증 시도
//     */
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        String userId = "hong12";
//        String password = "1234";
//
//        log.info("userId: " + userId);
//        log.info("password: " + password);
//
//        // 인증정보 객체 생성
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, password);
//
//        // 사용자 인증 (로그인 성공)
//        authentication = authenticationManager.authenticate(authentication);
//
//        log.info("인증 여부: " + authentication.isAuthenticated());
//
//        if(!authentication.isAuthenticated()) {
//            log.info("인증 실패!!!");
//            throw new NotFoundUserException(BAD_NOT_MATCH_MEMBER);
//        }
//
//        return authentication;
//        return null;
//    }
//
//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authentication
//    ) throws IOException, ServletException {
//        SecurityUserDetailDTO user = (SecurityUserDetailDTO) authentication.getPrincipal();
//
//        String userId = user.getUserModelDTO().getUserId();
//
//        String jwt = jwtTokenProvider.createAuthorizationToken(userId, 60 * 60 * 30);
//
//        // 헤더 설정
//        response.addHeader(JwtConstants.AUTH_TOKEN_HEADER, JwtConstants.AUTH_TOKEN_PREFIX + jwt);
//        response.setStatus(200);
//    }
//}
