package org.fianancetracker.backend.controllers;

import lombok.extern.slf4j.Slf4j;
import org.fianancetracker.backend.dto.ExpenseDTO;
import org.fianancetracker.backend.service.ExpenseService;
import org.fianancetracker.backend.util.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/expense")
@Slf4j
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/addExpense")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        log.info(this.getClass().getName() + "addExpense");
        String message = Validation.expenseValidation(expenseDTO);
        if (!message.isEmpty()) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        message = expenseService.addExpense(expenseDTO);
        if (message.isEmpty()) {
            return new ResponseEntity<>("Expense Added Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
