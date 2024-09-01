package com.konkov.CalculatorApp.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Service
public class VacationCalculatorServiceImpl implements VacationCalculatorService {

    private Set<LocalDate> holidays = Set.of(
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9));

    public double calculateVacationPay(double averageSalary, int vacationDays) {
        double dailySalary = averageSalary / 29.3;
        return dailySalary * vacationDays;
    }

    public long calculateWithDates(double averageSalary, LocalDate startDate, LocalDate endDate) {
        double dailySalary = averageSalary / 29.3;
        long daysBetween = startDate.datesUntil(endDate.plusDays(1)).filter(this::isWorkDay).count();

        return (long) dailySalary * daysBetween;
    }

    private boolean isWorkDay(LocalDate date) {
        return !(isHoliday(date) || isWeekend(date));
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }

}
