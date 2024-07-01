package org.fianancetracker.backend.controllers;


import lombok.extern.slf4j.Slf4j;
import org.fianancetracker.backend.dto.CategoryDTO;
import org.fianancetracker.backend.service.CategoryService;
import org.fianancetracker.backend.util.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info(this.getClass().getName() + "addCategory");
        String message = Validation.categoryValidation(categoryDTO);
        if (!message.isEmpty()) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        message = categoryService.addCategory(categoryDTO);
        if (message.isEmpty()) {
            return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info(this.getClass().getName() + "updateCategory");
        String message = Validation.categoryValidation(categoryDTO);
        if (!message.isEmpty()) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        message = categoryService.updateCategory(categoryDTO);
        if (message.isEmpty()) {
            return new ResponseEntity<>("Category Updated Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getCategory/{id}")
    @ResponseBody
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long id) {
        log.info(this.getClass().getName() + "Get Category");
        CategoryDTO category;

        category = categoryService.getCategoryById(id);
        if (!(category.getDescription() == null)) {
            return new ResponseEntity<>(category, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/getAllCategory")
    @ResponseBody
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        log.info(this.getClass().getName() + "Get All Category");
        List<CategoryDTO> categoryList;
        categoryList = categoryService.getAllCategories();
        if (!categoryList.isEmpty()) {
            return new ResponseEntity<>(categoryList, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(categoryList, HttpStatus.NO_CONTENT);
        }

    }

}
