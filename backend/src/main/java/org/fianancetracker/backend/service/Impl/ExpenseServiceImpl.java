package org.fianancetracker.backend.service.Impl;

import org.fianancetracker.backend.dto.ExpenseDTO;
import org.fianancetracker.backend.models.Category;
import org.fianancetracker.backend.models.Expense;
import org.fianancetracker.backend.repository.CategoryRepository;
import org.fianancetracker.backend.repository.ExpenseRepository;
import org.fianancetracker.backend.service.CommonService;
import org.fianancetracker.backend.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CommonService commonService;

    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CommonService commonService, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.commonService = commonService;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public String addExpense(ExpenseDTO expenseDTO) {
        String message = "";

        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setUser(commonService.findUserByUserName(expenseDTO.getUserId().toString()));

        LocalDate currentDate = LocalDate.now();
        expense.setDate(Date.valueOf(currentDate));

        Optional<Category> categoryOptional = categoryRepository.findById(expenseDTO.getType());
        if (categoryOptional.isPresent()) {
            expense.setType(categoryOptional.get());
        } else {
            return "Category not found";
        }

        try {
            expenseRepository.save(expense);
        } catch (Exception e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public List<ExpenseDTO> getAllExpense(String username) {

        LocalDate startDate = LocalDate.of(2024, 1, 15);
        LocalDate endDate = LocalDate.of(2024, 8, 30);

        List<Expense> expenseList = expenseRepository.findExpenseByMonth(username , startDate,endDate);

        if (!expenseList.isEmpty()) {
            return expenseList.stream()
                    .map(expense -> new ExpenseDTO(
                            expense.getId(),
                            expense.getDescription(),
                            expense.getUser().getUserName(),
                            expense.getAmount(),
                            expense.getType().getId()))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();

    }

}
