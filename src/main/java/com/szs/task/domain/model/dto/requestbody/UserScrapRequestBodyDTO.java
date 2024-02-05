package com.szs.task.domain.model.dto.requestbody;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "스크래핑 API Request Body")
public class UserScrapRequestBodyDTO {

    @Schema(name = "name", description = "회원 이름")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @Schema(name = "regNo", description = "주민등록번호")
    @NotBlank(message = "주민등록번호를 입력해주세요.")
    @Pattern(
            regexp = "\\d{6}-\\d{7}",
            message = "주민등록번호 형식으로 입력해주세요.(앞 6자리 - 뒷 7자리)")
    private String regNo;
}
