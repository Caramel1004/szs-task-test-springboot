package com.szs.task.domain.dao.Impl;

import com.szs.task.domain.dao.UserDAO;
import com.szs.task.domain.exception.InvalidDatabaseAccessException;
import com.szs.task.domain.exception.UnAuthorizationJwtException;
import com.szs.task.domain.model.dto.requestbody.UserLoginRequestBodyDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import com.szs.task.domain.model.entity.UserEntity;
import com.szs.task.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.*;


@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDAO {

    private final UserRepository userRepository;

    public void saveUser(UserModelDTO userDTO) throws RuntimeException {
        System.out.println(userDTO);
        userRepository.save(new UserEntity().toEntity(userDTO));
    }

    public boolean isExistingUser(UserLoginRequestBodyDTO dto) {
        return userRepository.existsByUserIdAndPassword(dto.getUserId(), dto.getPassword());
    }

    public boolean isExistingUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }


    public UserModelDTO getUserInfoByUserId(String userId) {
        return new ModelMapper().map(userRepository.findByUserId(userId), UserModelDTO.class);
    }

    public boolean getUserInfoByRegNoAndName(UserScrapRequestBodyDTO dto) {
        return userRepository.existsByRegNoAndName(dto.getRegNo(), dto.getName());
    }

}
