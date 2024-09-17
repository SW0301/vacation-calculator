package com.vacation_calculator.Controller;

import com.vacation_calculator.Model.VacationRequest;
import com.vacation_calculator.Service.VacationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class VacationController {
    private final VacationService vacationService;


    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/calculacte")
    public ResponseEntity<?> calculateVacationPay(
            @RequestParam BigDecimal averageSalary,
            @RequestParam(required = false) Integer vacationDays,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        if (startDate == null && endDate == null && vacationDays == null) {
            return ResponseEntity.badRequest().body("Необходимо указать либо дни отпуска, либо как начальную, так и конечную дату.");
        }
        if (startDate != null && endDate != null) {
            if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
                return ResponseEntity.badRequest().body("Дата начала отпуска не может быть позже даты окончания.");
            }
        }
        if (((startDate != null && endDate == null) || (startDate == null && endDate != null)) && vacationDays == null) {
            return ResponseEntity.badRequest().body("Как начальная, так и конечная дата должны указываться вместе.");
        }


        VacationRequest request = new VacationRequest();
        request.setAverageSalary(averageSalary);
        request.setVacationDays(vacationDays);
        request.setStartDate(startDate);
        request.setEndDate(endDate);

        return ResponseEntity.ok(vacationService.calculateVacationPay(request));
    }
}
