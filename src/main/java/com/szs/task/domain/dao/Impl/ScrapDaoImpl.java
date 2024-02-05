package com.szs.task.domain.dao.Impl;

import com.szs.task.domain.dao.ScrapDAO;
import com.szs.task.domain.model.dto.income.ExpenseModelDTO;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.model.entity.ExpenseEntity;
import com.szs.task.domain.model.entity.IncomeTaxEntity;
import com.szs.task.domain.repository.ExpenseRepository;
import com.szs.task.domain.repository.IncomeTaxRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScrapDaoImpl implements ScrapDAO {

    private final IncomeTaxRepository incomeTaxRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public void saveScrapDataOfUser(IncomeTaxModelDTO incomeTaxDTO, ExpenseModelDTO expenseDTO) {
        ExpenseEntity expenseEntity = expenseRepository.save(new ExpenseEntity().toEntity(expenseDTO)); // 보험료 의료비 교육비 기부금 퇴직금...
        incomeTaxRepository.save(new IncomeTaxEntity().toEntity(incomeTaxDTO, expenseEntity)); // 소득 세금 정보
    }
}
