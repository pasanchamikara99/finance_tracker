package org.fianancetracker.backend.service;

import org.fianancetracker.backend.dto.ExpenseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    String addExpense(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getAllExpense(String username);

    ExpenseDTO getBalance(String username);

}
