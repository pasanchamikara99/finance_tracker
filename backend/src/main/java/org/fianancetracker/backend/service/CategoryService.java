package org.fianancetracker.backend.service;

import org.fianancetracker.backend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    String addCategory(CategoryDTO categoryDTO);

    String updateCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();

    String deleteCategory(Long id);
}
