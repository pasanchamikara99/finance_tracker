package org.fianancetracker.backend.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private String id;
    private String description;
    private String type;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String description, String type) {
        this.id = String.valueOf(id);
        this.description = description;
        this.type = type;
    }
}
