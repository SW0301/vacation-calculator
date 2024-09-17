package com.vacation_calculator;

import com.vacation_calculator.Model.VacationRequest;
import com.vacation_calculator.Service.VacationServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.math.BigDecimal;

public class VacationServiceTest {
    private final VacationServiceImpl vacationService = new VacationServiceImpl();

    @Test
    public void testCalculateVacationpay() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(BigDecimal.valueOf(36500));
        request.setVacationDays(10);

        BigDecimal result = vacationService.calculateVacationPay(request);
        assertTrue(result.compareTo(BigDecimal.valueOf(1000)) == 0);
    }

    @Test
    public void testCalculateVacationPayWithDates() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(BigDecimal.valueOf(365000)); // Средняя зарплата
        request.setStartDate("2024-05-01"); // Дата начала отпуска
        request.setEndDate("2024-05-15"); // Дата окончания отпуска

        BigDecimal result = vacationService.calculateVacationPay(request);
        assertTrue(result.compareTo(BigDecimal.valueOf(9000)) == 0); // Учитывая праздники и выходные
    }

    @Test
    public void testCalculateVacationPayOnHolidays() {
        VacationRequest request = new VacationRequest();
        request.setAverageSalary(BigDecimal.valueOf(365000));
        request.setStartDate("2024-01-01");
        request.setEndDate("2024-01-01");

        BigDecimal result = vacationService.calculateVacationPay(request);
        assertTrue(result.compareTo(BigDecimal.valueOf(0)) == 0);
    }
}
