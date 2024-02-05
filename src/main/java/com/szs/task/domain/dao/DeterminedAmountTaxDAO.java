package com.szs.task.domain.dao;

import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface DeterminedAmountTaxDAO {

    IncomeTaxModelDTO getIncomeTaxByRegNo(String regNo);
}
