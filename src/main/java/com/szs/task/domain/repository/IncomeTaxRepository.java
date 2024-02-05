package com.szs.task.domain.repository;

import com.szs.task.domain.model.entity.IncomeTaxEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeTaxRepository extends JpaRepository<IncomeTaxEntity, Long> {
    @Query(value = "SELECT " +
            "    it.reg_no," +
            "    it.earned_income," +
            "    it.calculated_tax," +
            "    e.insurance_premium," +
            "    e.medical_expenses," +
            "    e.education_expenses," +
            "    e.donation_amount," +
            "    e.retirement_fund" +
            "FROM" +
            "    income_tax it" +
            "INNER JOIN" +
            "    expense e ON it.expense_id = e.expense_id" +
            "WHERE" +
            "    it.reg_no = :regNo;")
    IncomeTaxEntity findByRegNo(@Param("regNo") String regNo);
}
