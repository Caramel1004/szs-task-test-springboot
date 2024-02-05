package com.szs.task.service;

import com.szs.task.domain.model.dto.income.ExternalScrapResponseDTO;
import com.szs.task.domain.model.dto.requestbody.UserScrapRequestBodyDTO;
import com.szs.task.domain.service.impl.WebClientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@SpringBootTest(classes = WebClientServiceImpl.class)
public class WebClientServiceTest {

    @Test
    @DisplayName("스크래핑 테스트")
    public void getIncomeAndTaxTest() {
        UserScrapRequestBodyDTO dto = new UserScrapRequestBodyDTO();

        // given
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://codetest.3o3.co.kr")
                        .build();

//        Mono<ExternalScrapResponseDTO> response = webClient.post()
//                .uri(builder -> builder
//                        .path("/v2/scrap")
//                        .build()
//                )
//                .body(Mono.just(dto), ExternalScrapResponseDTO.class)
//                .exchangeToMono(res-> {
//                    return res.bodyToMono(ExternalScrapResponseDTO.class);
//                })
////                .retrieve()
//                .block();

        // 배열 json으로 넘어온 데이터를 List로 변환
//        List<Object> list = response.block();


        // ExchangeRateInfoDTO 참조형인 각각의 요소들을 hash로 저장 USD or USD,JPY
//        HashMap<String, Object> exchangeRateInfoHash = new HashMap<String, Object>();
//        for(Object dto: exchangeRateInfoList) {
//            exchangeRateInfoHash.put(dto.Object(),dto);
//        }

//        System.out.println(response);
    }
}
