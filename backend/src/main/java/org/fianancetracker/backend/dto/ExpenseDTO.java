package org.fianancetracker.backend.dto;

import lombok.Data;
import org.fianancetracker.backend.models.Category;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data

public class ExpenseDTO {

    private Long id;
    private String userId;
    private String description;
    private Long amount;
    private Long type;
    private Date date;

    public ExpenseDTO(Long id, String userId, String description, Long amount, Long type) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }
}
