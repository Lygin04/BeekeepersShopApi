package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.entity.Category;
import com.lygin.beekeepersshopapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(Category category){
        Category newCategory = categoryService.create(category);
        return ResponseEntity.ok(newCategory);
    }
}
