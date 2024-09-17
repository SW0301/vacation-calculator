package com.vacation_calculator.Service;

import com.vacation_calculator.Model.VacationRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface VacationService {
     BigDecimal calculateVacationPay(VacationRequest request);
}
