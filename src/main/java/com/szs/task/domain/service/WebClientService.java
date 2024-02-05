package com.szs.task.domain.service;

import com.szs.task.domain.model.dto.income.ExternalScrapResponseDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import org.springframework.stereotype.Service;

@Service
public interface WebClientService {

    ExternalScrapResponseDTO getScrapData(UserScrapRequestBodyDTO body);

    boolean isMatchedUserNameAndRegNo(UserModelDTO body);
}
