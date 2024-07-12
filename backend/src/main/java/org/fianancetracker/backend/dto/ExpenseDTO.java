package org.fianancetracker.backend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class ExpenseDTO {

    private Long id;
    private Long userId;
    private String description;
    private Long amount;
    private String type;
    private Date date;
}
