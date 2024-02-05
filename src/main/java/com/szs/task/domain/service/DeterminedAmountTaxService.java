package com.szs.task.domain.service;

import com.szs.task.domain.model.dto.income.DeterminedTaxModelDTO;
import org.springframework.stereotype.Service;

@Service
public interface DeterminedAmountTaxService {

    DeterminedTaxModelDTO getDeterminedAmountTaxOfUser(String accessToken);

}
