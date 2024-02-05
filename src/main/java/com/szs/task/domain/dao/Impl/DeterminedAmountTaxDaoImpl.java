package com.szs.task.domain.dao.Impl;

import com.szs.task.domain.dao.DeterminedAmountTaxDAO;
import com.szs.task.domain.exception.InvalidDatabaseAccessException;
import com.szs.task.domain.model.dto.income.IncomeTaxModelDTO;
import com.szs.task.domain.repository.IncomeTaxRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import static com.szs.task.domain.model.vo.HttpResponseStatusCodeNameVO.INTERNAL_SERVER_DATABASE_ERROR;

@Repository
@RequiredArgsConstructor
public class DeterminedAmountTaxDaoImpl implements DeterminedAmountTaxDAO {

    private final IncomeTaxRepository incomeTaxRepository;

    @Override
    public IncomeTaxModelDTO getIncomeTaxByRegNo(String regNo) throws IllegalArgumentException {
        try {
            return new ModelMapper().map(incomeTaxRepository.findByRegNo(regNo), IncomeTaxModelDTO.class);
        } catch (IllegalArgumentException e) {
            throw new InvalidDatabaseAccessException(INTERNAL_SERVER_DATABASE_ERROR);
        }
    }
}
