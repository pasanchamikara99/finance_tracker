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

    @Query("SELECT E.type.description, SUM(E.amount) FROM Expense E WHERE E.user.email = :email AND E.date BETWEEN :startDate AND :endDate AND E.type.type = 'Expence' GROUP BY E.type")
    List<Object[]> findExpenseByMonth(
            @Param("email") String email,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT E.type.type, SUM(E.amount) FROM Expense E WHERE E.user.email = :email AND E.date BETWEEN :startDate AND :endDate AND E.type.type = 'Income' GROUP BY E.type.type")
    List<Object[]> findIncomeByMonth(
            @Param("email") String email,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
