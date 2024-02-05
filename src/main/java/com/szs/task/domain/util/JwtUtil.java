package com.szs.task.domain.util;

import com.szs.task.domain.configration.JwtConfigProps;

import com.szs.task.domain.exception.InValidRequestHeaderException;
import com.szs.task.domain.exception.UnAuthorizationJwtException;
import com.szs.task.domain.security.jwt.constants.JwtConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.*;
import static com.szs.task.domain.security.jwt.constants.JwtConstants.JWT_SECRET_KEY;

/**
 * HTTP
 *     headers : {
 *			Authorization : Bearer ${jwt}
 * 	   }
 */
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {


    private final JwtConfigProps jwtConfigProps;

    /**
     * 1. 인증 토큰 생성
     *
     * @Param String userId: 유저 아이디
     * @Param long second: 시간 초
     * @Return String jwt: 인증 토큰(jsonwebtoken)
     * secretKey: 영문 문자열 64
     * HMAC-SHA512 알고리즘 사용
     * 영어문자 하나당 1bit => 512bit = 64byte = 8bit X 8bit => 문자열 길이 64
     */
    public static String createAuthorizationToken(String userId, long second) {
//        String secretKey = "l4(&:+*kc&uf$<0q5(9(n]cNricB'Yx(1l;YEsKE83l]lCOJrsg,YT\\LL4yH7G1K";
        return Jwts.builder()
                // 영어문자 하나당 1bit => 512bit = 64byte = 8bit X 8bit => 문자열 길이 64
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()), Jwts.SIG.HS512)
                .header()
                .add("typ", JwtConstants.AUTH_TOKEN_TYPE)
                .and()
                .expiration(new Date(System.currentTimeMillis() + second))
                .claim("userId", userId)
                .compact();
    }

    /**
     * 2. 인증 토큰 유효성 검사
     *
     * @Param String jwt: 인증 토큰
     * @Return boolean token: 사용자 정보를 담은 인가 토큰 객체
     * secretKey: 영문 문자열 64
     * HMAC-SHA512 알고리즘 사용
     * 영어문자 하나당 1bit => 512bit = 64byte = 8bit X 8bit => 문자열 길이 64
     */
    public static boolean isExpiredJWT (String jwt) {
        log.info(jwt);
        try {
            // JWT Parsing
            Jws<Claims> parsedToken = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()))
                    .build()
                    .parseSignedClaims(jwt);

            Date exp = parsedToken.getPayload().getExpiration();

            // 만료시간, 현재시간 비교
            // true: 만료, false: 유효
            boolean isExpiredJWT = exp.before(new Date());

            return isExpiredJWT;
        } catch (ExpiredJwtException e) {       // 토큰 만료
            throw new UnAuthorizationJwtException(UNAUTHORIZED_EXPIRED_JWT);
        } catch (MalformedJwtException | IllegalArgumentException e) {      // JWT 형태가 아닌 토큰 or 잘못된 토큰
            throw new UnAuthorizationJwtException(UNAUTHORIZED_MALFORMED_JWT);
        } catch (NullPointerException e) {
            throw new UnAuthorizationJwtException(UNAUTHORIZED_NOT_EXIST_JWT);
        }
    }

    /** 3. 인증 토큰 파싱
     *
     * @Param String authHeader: 인증 요청 헤더
     * @Return UsernamePasswordAuthenticationToken token: 사용자 정보를 담은 인가 토큰 객체
     * secretKey: 영문 문자열 64
     * HMAC-SHA512 알고리즘 사용
     * 영어문자 하나당 1bit => 512bit = 64byte = 8bit X 8bit => 문자열 길이 64
     */
    public static String parseAccessToken(String accessToken) {
        log.info(accessToken);
        if (accessToken == null || accessToken.isEmpty()) {
            throw new InValidRequestHeaderException(BAD_REQUEST_HEADER);
        }

        try {
            // JWT Parsing
            Jws<Claims> parsedToken = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes()))
                    .build()
                    .parseSignedClaims(accessToken);

            String userId = parsedToken.getPayload().get("userId").toString();

            if (userId == null || userId.isEmpty()) {
                throw new UnAuthorizationJwtException(UNAUTHORIZED_PAYLOAD_MEMBER_DATA);
            }

            return userId;
        } catch (ExpiredJwtException e) {       // 토큰 만료
            throw new UnAuthorizationJwtException(UNAUTHORIZED_EXPIRED_JWT);
        } catch (MalformedJwtException | IllegalArgumentException e) {      // JWT 형태가 아닌 토큰 or 잘못된 토큰
            throw new UnAuthorizationJwtException(UNAUTHORIZED_MALFORMED_JWT);
        }
    }
}
