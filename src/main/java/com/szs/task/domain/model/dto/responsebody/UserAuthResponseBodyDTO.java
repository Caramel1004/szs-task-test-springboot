package com.szs.task.domain.model.dto.responsebody;

import com.szs.task.domain.model.vo.HttpResponseModelVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "SUCCESS_MEMBER_LOGIN: 로그인 Response Body")
public class UserAuthResponseBodyDTO {

    @Schema(name = "result", description = "HTTP 상태 응답 모델")
    private HttpResponseModelVO result;

    @Schema(name = "accessToken", description = "인증토큰")
    private String accessToken;
}
