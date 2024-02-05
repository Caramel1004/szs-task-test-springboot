package com.szs.task.domain.model.dto.income;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "특별세액공제금액 계산 할 때 필요한 스크랩 데이터")
public class ExpenseModelDTO {

    @Schema(name = "보험료납입액", description = "insurancePremium")
    @JsonProperty("보험료납입액")
    private BigDecimal insurancePremium;

    @Schema(name = "의료비납입액", description = "medicalExpenses")
    @JsonProperty("의료비납입액")
    private BigDecimal medicalExpenses;

    @Schema(name = "교육비납입액", description = "educationExpenses")
    @JsonProperty("교육비납입액")
    private BigDecimal educationExpenses;

    @Schema(name = "기부금", description = "donationAmount")
    @JsonProperty("기부금")
    private BigDecimal donationAmount;

    @Schema(name = "퇴직연금납입액", description = "retirementFund")
    @JsonProperty("퇴직연금납입액")
    private BigDecimal retirementFund;

    public ExpenseModelDTO() {

    }

    public ExpenseModelDTO(ExternalScrapResponseDTO dto) {
        this.insurancePremium = new BigDecimal(dto.getData().getJsonList().getIncomeDeduction().get(0).getCost().replaceAll(",", ""));
        this.medicalExpenses = new BigDecimal(dto.getData().getJsonList().getIncomeDeduction().get(1).getCost().replaceAll(",", ""));
        this.educationExpenses = new BigDecimal(dto.getData().getJsonList().getIncomeDeduction().get(2).getCost().replaceAll(",", ""));
        this.donationAmount = new BigDecimal(dto.getData().getJsonList().getIncomeDeduction().get(3).getCost().replaceAll(",", ""));
        this.retirementFund = new BigDecimal(dto.getData().getJsonList().getIncomeDeduction().get(4).getCost().replaceAll(",", ""));
    }
}
