package com.lygin.beekeepersshopapi.services.impl;

import com.lygin.beekeepersshopapi.dto.CategoryDto;
import com.lygin.beekeepersshopapi.entity.Category;
import com.lygin.beekeepersshopapi.repositories.CategoryRepository;
import com.lygin.beekeepersshopapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepository.save(category);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}
