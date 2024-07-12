package org.fianancetracker.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Expense")
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "create_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
