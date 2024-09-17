package com.vacation_calculator.Service;

import com.vacation_calculator.Model.VacationRequest;
import org.springframework.stereotype.Service;
import org.w3c.dom.html.HTMLOptGroupElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class VacationServiceImpl implements VacationService {

    //праздники
    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();

    static {

        HOLIDAYS.add(LocalDate.of(2024, 1, 1)); // Новый Год
        HOLIDAYS.add(LocalDate.of(2024, 5, 1)); // День Труда
        HOLIDAYS.add(LocalDate.of(2024, 5, 9)); // День Победы
        HOLIDAYS.add(LocalDate.of(2024,2, 23)); // 23 февраля
        HOLIDAYS.add(LocalDate.of(2024, 3, 8)); // 8 марта
        HOLIDAYS.add(LocalDate.of(2024, 6, 12)); // День России
        // todo добавить праздники
    }

    public BigDecimal calculateVacationPay(VacationRequest request) {
        BigDecimal dailySalary = request.getAverageSalary().divide(BigDecimal.valueOf(365), 2, RoundingMode.HALF_UP);

        if (request.getStartDate() != null && request.getEndDate() != null) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(request.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(request.getEndDate(), formatter);

            int vacationDays = calculateBusinessDaysBetween(startDate, endDate);
            return dailySalary.multiply(BigDecimal.valueOf(vacationDays));
        }

        // Если точные даты не указаны, простой расчет на основе количества дней
        return dailySalary.multiply(BigDecimal.valueOf(request.getVacationDays()));
    }

    private int calculateBusinessDaysBetween(LocalDate startDate, LocalDate endDate) {
        int businessDays = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (isBusinessDay(date)) {
                businessDays++;
            }
        }

        return businessDays;
    }

    private boolean isBusinessDay(LocalDate date) {
        // Проверяем, что день не является выходным (суббота/воскресенье) или праздничным
        return !(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || HOLIDAYS.contains(date));
    }
}
