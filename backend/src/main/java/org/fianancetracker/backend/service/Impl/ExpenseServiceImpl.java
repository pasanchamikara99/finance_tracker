package org.fianancetracker.backend.service.Impl;

import org.fianancetracker.backend.dto.ExpenseDTO;
import org.fianancetracker.backend.models.Category;
import org.fianancetracker.backend.models.Expense;
import org.fianancetracker.backend.repository.CategoryRepository;
import org.fianancetracker.backend.repository.ExpenseRepository;
import org.fianancetracker.backend.repository.UserRepository;
import org.fianancetracker.backend.service.CommonService;
import org.fianancetracker.backend.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CommonService commonService;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CommonService commonService, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.commonService = commonService;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public String addExpense(ExpenseDTO expenseDTO) {
        String message = "";

        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setUser(userRepository.getReferenceByEmail(expenseDTO.getUserId().toString()));

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

        List<ExpenseDTO> expenseDTOList = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        LocalDate startDate = LocalDate.of(currentYear, currentMonth, 1);
        LocalDate endDate = LocalDate.of(currentYear, currentMonth, 30);

        List<Object[]> results = expenseRepository.findExpenseByMonth(username, startDate, endDate);

        for (Object[] result : results) {
            ExpenseDTO expenseDTO = new ExpenseDTO();
            expenseDTO.setAmount((long) result[1]);
            expenseDTO.setTypeDescription((String) result[0]);
            expenseDTOList.add(expenseDTO);
        }

        return expenseDTOList;

    }

}
