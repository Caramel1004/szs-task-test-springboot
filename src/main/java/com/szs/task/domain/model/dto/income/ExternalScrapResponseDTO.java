package com.szs.task.domain.model.dto.income;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalScrapResponseDTO {
    @JsonProperty
    private String status;

    @JsonProperty("data")
    private JsonList data;

    @Data
    public static class JsonList {
        private IncomeTax jsonList;
    }

    @Data
    public static class IncomeTax {

        @JsonProperty("급여")
        private List<Salary> salary;

        @JsonProperty("산출세액")
        private String calculatedTax;

        @JsonProperty("소득공제")
        private List<Expense> incomeDeduction;

    }

    @Data
    public static class Salary {
        @JsonProperty("총지급액")
        private String income;

        @JsonProperty("주민등록번호")
        private String regNo;

    }

    @Data
    public static class Expense {
        @JsonAlias({"금액", "총납임금액"})
        private String cost;

        @JsonProperty("소득구분")
        private String type;
    }
}
