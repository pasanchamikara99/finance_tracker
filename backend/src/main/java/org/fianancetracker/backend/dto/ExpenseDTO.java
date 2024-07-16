package org.fianancetracker.backend.dto;

import lombok.Data;

import java.sql.Date;

@Data

public class ExpenseDTO {

    private Long id;
    private String userId;
    private String description;
    private String typeDescription;
    private Long amount;
    private Long type;
    private Date date;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Long id, String description, String userId, Long amount, String type) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.typeDescription = type;
    }
}
