package com.vacation_calculator.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class VacationRequest {
    private BigDecimal averageSalary;
    private int vacationDays;
    private String startDate;
    private String endDate;

}
