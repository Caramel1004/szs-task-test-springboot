package com.szs.task.domain.model.dto.income;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.szs.task.domain.model.vo.HttpResponseModelVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "회원의 결정세액, 공제액 데이터")
public class DeterminedTaxModelDTO {

    @Schema(name = "회원 이름", description = "name")
    @JsonProperty("이름")
    private String name;

    @Schema(name = "결정세액", description = "determinedTax")
    @JsonProperty("결정세액")
    private String determinedTax;

    @Schema(name = "퇴직연금세액공제", description = "retirementPensionTaxDeduction")
    @JsonProperty("퇴직연금세액공제")
    private String retirementPensionTaxDeduction;

    public DeterminedTaxModelDTO() {

    }

    public DeterminedTaxModelDTO(String name, String determinedTax, String retirementPensionTaxDeduction) {
        this.name = name;
        this.determinedTax = determinedTax;
        this.retirementPensionTaxDeduction = retirementPensionTaxDeduction;
    }
}
