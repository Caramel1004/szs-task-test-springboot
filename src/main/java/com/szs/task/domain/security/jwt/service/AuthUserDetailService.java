//package com.szs.task.domain.security.jwt.service;
//
//import com.szs.task.domain.dao.UserDAO;
//import com.szs.task.domain.exception.NotFoundUserException;
//import com.szs.task.domain.security.jwt.dto.SecurityUserDetailDTO;
//import com.szs.task.domain.model.dto.user.UserModelDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.BAD_NOT_MATCH_MEMBER;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class AuthUserDetailService implements UserDetailsService {
//
//    private final UserDAO userDAO;
//
//    /**
//     * Spring Security => 유저 정보 조회 => 인증 처리
//     * */
//    @Override
//    public UserDetails loadUserByUsername(String userId) {
//        System.out.println("'" + userId + "' 조회 중......");
//
//        UserModelDTO userDTO = userDAO.getUserInfoByUserId(userId);
//
//        /*
//        * @Exception NotFoundUserException: DB에 일치하는 유저 없는 경우
//        * */
//        if(userDTO == null) {
//            throw new NotFoundUserException(BAD_NOT_MATCH_MEMBER);
//        }
//
//        log.info("'" + userId + "' 존재");
//
////        return new SecurityUserDetailDTO(userDTO);
//        return null;
//    }
//}
