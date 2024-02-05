package com.szs.task.util;


import com.szs.task.domain.util.DeterminedTaxCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.verify;

@ContextConfiguration(classes = DeterminedTaxCalculator.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DeterminedTaxCalculatorTest {

    @MockBean
    DeterminedTaxCalculator determinedTaxCalculator;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("------결정 세액 계산 테스트------");
    }

    @Test
    @DisplayName("세액 계산")
    public void calculatorTax() {
//        String determinedTax = taxCalculator.calculatorTaxAndFormatToKRW(SavedScrapDataDTO);
//        Assertions.assertTrue(true, determinedTax);
//        Assertions.assertEquals("0", determinedTax);
//        Mockito.when(taxCalculator.calculatorTax())
//                        .thenReturn("60000000");
//        verify(taxCalculator).calculatorTax();
    }
}
