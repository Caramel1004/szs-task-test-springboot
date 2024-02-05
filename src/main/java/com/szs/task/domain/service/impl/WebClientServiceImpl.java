package com.szs.task.domain.service.impl;

import com.szs.task.domain.model.dto.income.ExternalScrapResponseDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.model.dto.user.UserModelDTO;
import com.szs.task.domain.service.WebClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientServiceImpl implements WebClientService {

    @Override
    public ExternalScrapResponseDTO getScrapData(UserScrapRequestBodyDTO body) {

        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://codetest.3o3.co.kr")
                        .build();

        Mono<ExternalScrapResponseDTO> response = webClient.post()
                .uri(builder -> builder
                        .path("/v2/scrap")
                        .build()
                )
                .bodyValue(body)
                .retrieve()
                .bodyToMono(ExternalScrapResponseDTO.class);

        return response.block();
    }

    @Override
    public boolean isMatchedUserNameAndRegNo(UserModelDTO user) {
        boolean isMatchedUser = false;

        // webClient 기본 설정
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://codetest.3o3.co.kr")
                        .build();

        Mono<ExternalScrapResponseDTO> response = webClient.post()
                .uri(builder -> builder
                        .path("/v2/scrap")
                        .build()
                )
                .bodyValue(user)
                .retrieve()
                .bodyToMono(ExternalScrapResponseDTO.class);

        ExternalScrapResponseDTO scrap = response.block();

        //  스크랩 데이터 HTTP 응답 상태 체크
        if(scrap.getStatus().equalsIgnoreCase("success") && scrap.getStatus() != null) {
            isMatchedUser = true;
        }

        return isMatchedUser;
    }
}
