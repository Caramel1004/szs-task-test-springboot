package com.szs.task.domain.model.entity;

import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "income_tax")
public class IncomeTaxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="income_tax_id")
    private Long incomeTaxId;

    @Column(name="regNo", unique = true, nullable = false)
    private String regNo;

    @Column(name="earned_income", nullable = false)
    private BigDecimal earnedIncome;

    @Column(name="calculated_tax", nullable = false)
    private BigDecimal calculatedTax;

    @OneToOne
    @JoinColumn(name = "expense_id") // 외래 키 이름
    private ExpenseEntity expense;

    public IncomeTaxEntity toEntity (IncomeTaxModelDTO dto, ExpenseEntity expenseEntity) {
        return IncomeTaxEntity.builder()
                .regNo(dto.getRegNo())
                .earnedIncome(dto.getEarnedIncome())
                .calculatedTax(dto.getCalculatedTax())
                .expense(expenseEntity)
                .build();
    }

}
