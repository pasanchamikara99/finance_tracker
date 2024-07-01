package org.fianancetracker.backend.service.Impl;

import org.fianancetracker.backend.dto.CategoryDTO;
import org.fianancetracker.backend.models.Category;
import org.fianancetracker.backend.repository.CategoryRepository;
import org.fianancetracker.backend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String addCategory(CategoryDTO categoryDTO) {
        String message = "";
        try {
            Category category = new Category();
            category.setDescription(categoryDTO.getDescription());
            category.setType(categoryDTO.getType());
            categoryRepository.save(category);
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String updateCategory(CategoryDTO categoryDTO) {
        String message = "";


        Optional<Category> categoryOptional = categoryRepository.findById(Long.parseLong(categoryDTO.getId()));

        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.setDescription(categoryDTO.getDescription());
            category.setType(categoryDTO.getType());
            try {
                categoryRepository.save(category);
            } catch (Exception e) {
                message = e.getMessage();
            }
        } else {
            message = "No category Found";
        }

        return message;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        CategoryDTO categoryDTO = new CategoryDTO();

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setType(category.getType());
        }

        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (!categories.isEmpty()) {
            return categories.stream()
                    .map(category -> new CategoryDTO(
                            category.getId(),
                            category.getDescription(),
                            category.getType()))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public String deleteCategory(Long id) {
        String message = "";
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            try {
                categoryRepository.delete(categoryOptional.get());
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            message = "No Category Found";
        }
        return message;
    }
}
