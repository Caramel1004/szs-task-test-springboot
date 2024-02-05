//package com.szs.task.domain.configration;
//
//import com.szs.task.domain.security.jwt.service.AuthUserDetailService;
//import com.szs.task.domain.security.jwt.filter.JwtAuthenticationFilter;
//import com.szs.task.domain.security.jwt.filter.JwtRequestFilter;
//import com.szs.task.domain.security.jwt.provider.JwtTokenProvider;
//import lombok.extern.slf4j.Slf4j;
////import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
////    @Autowired
////    private AuthUserDetailService authUserDetailService;
////
////
////    private JwtTokenProvider jwtTokenProvider;
////
////
////    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private DecodedKeyConfigProps decodedKeyConfigProps;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        // 폼 로그인 비활성화
//        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
//
//        // HTTP 기본 비활성화
//        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
//
//        // csrf 공격 방어 기능 비활성화
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//
//
//        // 세션 비활성화 => JWT로 인증
//        httpSecurity.sessionManagement(management ->
//                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//
//        // 인가 설정: 인증이 필요한 요청 등록 authenticated, 인증이 필요없는 요청 등록 permitAll
//        httpSecurity.authorizeHttpRequests(req -> req
//                        .requestMatchers("/szs/signup").permitAll()
//                        .requestMatchers("/szs/login").permitAll()
//                        .requestMatchers("/szs/scrap").permitAll()
//                        .requestMatchers("/szs/refund").permitAll()
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
//                        .requestMatchers("/api/v1/szs-api", "/swagger-ui/*").permitAll()
//        );
//
//        /*
//         * 인증 방식 설정
//         *   - 인메모리 방식
//         *   - JDBC 방식
//         *   - 커스텀 방식
//         * 적용: 커스텀 방식
//         * */
////        httpSecurity.userDetailsService(authUserDetailService);
////
////
////        /*
////         * 필터 설정
////         * 1. JWT Request Filter
////         *   - JWT 해석
////         * 2. JWT Filter(Login)
////         *   - @param userId, password
////         *   - 토큰 생성
////         *
////         * JwtAuthenticationFilter(아이디 비번 일치하는지 부터 확인 => "/szs/login" 엔드 포인트만 이 필터에서 토큰 생성) => JwtRequestFilter
////         * */
////        httpSecurity.addFilterAt(new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) // 설정할 필터 인스턴스
////                .addFilterBefore(new JwtRequestFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//
//        return httpSecurity.build();
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AesBytesEncryptor aesBytesEncryptor() {
////        return new AesBytesEncryptor(decodedKeyConfigProps.getSecretKey(), "70726574657374");
////    }
//
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
////        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
////
////        return authenticationManager;
////    }
//}
