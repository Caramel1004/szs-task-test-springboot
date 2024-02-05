//package com.szs.task.domain.security.jwt.filter;
//
//import com.szs.task.domain.exception.UnAuthorizationJwtException;
//import com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO;
//import com.szs.task.domain.security.jwt.constants.JwtConstants;
//import com.szs.task.domain.security.jwt.provider.JwtTokenProvider;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.UNAUTHORIZED_EXPIRED_JWT;
//
////@Component
////@RequiredArgsConstructor
//public class JwtRequestFilter extends OncePerRequestFilter {
////    private final JwtTokenProvider jwtTokenProvider;
//
//
//    /**
//     * JWT 요청 필터
//     * - JWT 유효성 검사
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        String header = request.getHeader(JwtConstants.AUTH_TOKEN_HEADER);
////
////        // 엔드포인트가 "/szs/login" 일때만 통과 => 토큰이 없는 경우
////        if(header == null || header.isEmpty() || !header.startsWith(JwtConstants.AUTH_TOKEN_PREFIX)) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        /*
////        * 이 부분부터는 헤더가 있는 경우 이므로 토큰만 추출 "Bearer " + JWT
////        * */
////        String token = header.split(JwtConstants.AUTH_TOKEN_PREFIX)[1];
////
////        Authentication authentication = jwtTokenProvider.getAuthentication(token);
////
////        if(jwtTokenProvider.isExpiredJWT(token)) {
////            throw new UnAuthorizationJwtException(UNAUTHORIZED_EXPIRED_JWT);
////        }
////
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        filterChain.doFilter(request, response);
//    }
//}
