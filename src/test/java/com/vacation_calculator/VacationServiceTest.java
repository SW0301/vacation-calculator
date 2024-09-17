package com.vacation_calculator;

import com.vacation_calculator.Model.VacationRequest;
import com.vacation_calculator.Service.VacationService;
import com.vacation_calculator.Service.VacationServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

public class VacationServiceTest {
    private final VacationService vacationService = new VacationServiceImpl();

    @Test
    public void testCalculateVacationpay() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(BigDecimal.valueOf(36500));
        request.setVacationDays(10);

        BigDecimal result = vacationService.calculateVacationPay(request);
        assertTrue(result.compareTo(BigDecimal.valueOf(1000)) == 0);
    }
}
