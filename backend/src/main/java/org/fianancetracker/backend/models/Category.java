package org.fianancetracker.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false, referencedColumnName = "user_name")
    private User user;

    @Column(name = "create_date")
    private Date date;
}
