package com.szs.task.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.HashMap;

@Service
public interface ScrapService {

    IncomeTaxModelDTO saveScrapDataOfUser(UserScrapRequestBodyDTO dto, String accessToken) throws WebClientRequestException;
}
