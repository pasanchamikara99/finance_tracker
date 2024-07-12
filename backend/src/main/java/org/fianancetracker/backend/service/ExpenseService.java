package org.fianancetracker.backend.service;

import org.fianancetracker.backend.dto.ExpenseDTO;
import org.springframework.stereotype.Service;

@Service
public interface ExpenseService {

    String addExpense(ExpenseDTO expenseDTO);

}
