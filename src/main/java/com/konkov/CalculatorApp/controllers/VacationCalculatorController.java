package com.konkov.CalculatorApp.controllers;

import com.konkov.CalculatorApp.services.VacationCalculatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;

    @Autowired
    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }


    @GetMapping
    public ResponseEntity<String> calculate(@RequestParam double averageSalary,
                                            @RequestParam(required = false) Integer vacationDays,
                                            @RequestParam(required = false) @DateTimeFormat LocalDate startDate,
                                            @RequestParam(required = false) @DateTimeFormat LocalDate endDate) {
        double result;
        if (vacationDays != null) {
            result = vacationCalculatorService.calculateVacationPay(averageSalary, vacationDays);
        } else if (startDate != null && endDate != null) {
            result = vacationCalculatorService.calculateWithDates(averageSalary, startDate, endDate);
        } else {
            return ResponseEntity.badRequest().body("Необходимо указать количество дней отпуска или даты начала и конца отпуска");
        }

        return ResponseEntity.ok("Ваши отпускные = " + result);
    }

}
