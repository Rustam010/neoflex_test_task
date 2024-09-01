package com.konkov.CalculatorApp.services;

import java.time.LocalDate;

public interface VacationCalculatorService {

    double calculateVacationPay(double averageSalary, int vacationDays);
    long calculateWithDates(double averageSalary, LocalDate startDate, LocalDate endDate);

}
