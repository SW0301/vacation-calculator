package com.vacation_calculator.Service;

import com.vacation_calculator.Model.VacationRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationServiceImpl implements VacationService{

    public BigDecimal calculateVacationPay(VacationRequest request) {

        BigDecimal dailySalary = request.getAverageSalary().divide(BigDecimal.valueOf(365),2, RoundingMode.HALF_UP);
        return dailySalary.multiply(BigDecimal.valueOf(request.getVacationDays()));
    }

}
