package org.fianancetracker.backend.models;

import jakarta.persistence.*;
import lombok.Data;

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


}
