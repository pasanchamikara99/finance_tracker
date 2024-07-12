package org.fianancetracker.backend.service;

import org.fianancetracker.backend.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    String addCategory(CategoryDTO categoryDTO);

    String updateCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    String deleteCategory(Long id);

    List<CategoryDTO> getCategoryByType(String type);
}
