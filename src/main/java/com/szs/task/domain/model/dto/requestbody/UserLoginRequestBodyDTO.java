package com.szs.task.domain.model.dto.requestbody;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "로그인 API Request Body")
public class UserLoginRequestBodyDTO {

    @Schema(description = "유저 아이디")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @Schema(description = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
