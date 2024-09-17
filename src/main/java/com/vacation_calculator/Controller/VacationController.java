package com.vacation_calculator.Controller;

import com.vacation_calculator.Model.VacationRequest;
import com.vacation_calculator.Service.VacationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class VacationController {
    private final VacationService vacationService;


    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/calculacte")
    public BigDecimal calculateVacationPay(
            @RequestParam BigDecimal averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        VacationRequest request = new VacationRequest();
        request.setAverageSalary(averageSalary);
        request.setVacationDays(vacationDays);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        return vacationService.calculateVacationPay(request);
    }
}
