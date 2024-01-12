package com.lygin.beekeepersshopapi.controllers;

import com.lygin.beekeepersshopapi.dto.CategoryDto;
import com.lygin.beekeepersshopapi.entity.Category;
import com.lygin.beekeepersshopapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto category){
        Category newCategory = categoryService.create(category);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }
}
