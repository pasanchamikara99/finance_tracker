package org.fianancetracker.backend.repository;

import org.fianancetracker.backend.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT E FROM Expense E WHERE E.user.userName = :username AND E.date BETWEEN :startDate AND :endDate")
    List<Expense> findExpenseByMonth(
            @Param("username") String username,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
