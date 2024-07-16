package org.fianancetracker.backend.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryDTO {
    private String id;
    private String description;
    private String type;
    private String username;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String description, String type, String username) {
        this.id = String.valueOf(id);
        this.description = description;
        this.type = type;
        this.username = username;
    }
}
