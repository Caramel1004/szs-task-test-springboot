package com.szs.task.domain.model.dto.income;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "회원의 소득정보, 주민등록번호: 결정세액 계산할 때 필요한 스크랩 데이터")
public class IncomeTaxModelDTO {

    @Schema(name = "주민등록번호", description = "regNo")
    @JsonProperty("주민등록번호")
    private String regNo;

    @Schema(name = "근로소득", description = "earnedIncome")
    @JsonProperty("근로소득")
    private BigDecimal earnedIncome;

    @Schema(name = "산출세액", description = "calculatedTax")
    @JsonProperty("산출세액")
    private BigDecimal calculatedTax;

    @Schema(name = "납입정보", description = "expense")
    @JsonProperty("납입정보")
    private ExpenseModelDTO expense;

    public IncomeTaxModelDTO() {

    }

    public IncomeTaxModelDTO(ExternalScrapResponseDTO dto, ExpenseModelDTO expenseDTO) {
        this.regNo = dto.getData().getJsonList().getSalary().get(0).getRegNo();
        this.earnedIncome = new BigDecimal(dto.getData().getJsonList().getSalary().get(0).getIncome().replaceAll(",", ""));
        this.calculatedTax = new BigDecimal(dto.getData().getJsonList().getCalculatedTax().replaceAll(",", ""));
        this.expense = expenseDTO;
    }
}
