package com.szs.task.domain.service;

import com.szs.task.domain.model.dto.requestbody.UserLoginRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(UserModelDTO userDTO);

    String getAuthToken(UserLoginRequestBodyDTO reqDTO);
}
