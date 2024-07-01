package org.fianancetracker.backend.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
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
