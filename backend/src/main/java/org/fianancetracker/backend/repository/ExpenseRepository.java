package org.fianancetracker.backend.repository;

import org.fianancetracker.backend.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
