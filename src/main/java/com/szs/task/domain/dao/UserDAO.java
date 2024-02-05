package com.szs.task.domain.dao;

import com.szs.task.domain.model.dto.requestbody.UserLoginRequestBodyDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    boolean isExistingUser(UserLoginRequestBodyDTO userLoginRequestBodyDTO);

    boolean getUserInfoByRegNoAndName(UserScrapRequestBodyDTO dto);

    boolean isExistingUserId(String userId);

    UserModelDTO getUserInfoByUserId(String userId);

    void saveUser(UserModelDTO dto);
}
