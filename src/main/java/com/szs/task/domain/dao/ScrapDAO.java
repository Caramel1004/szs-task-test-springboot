package com.szs.task.domain.dao;

import com.szs.task.domain.model.dto.income.ExpenseModelDTO;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapDAO {

    void saveScrapDataOfUser(IncomeTaxModelDTO incomeTaxDTO, ExpenseModelDTO expenseDTO);
}
