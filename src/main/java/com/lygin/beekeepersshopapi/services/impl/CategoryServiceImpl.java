package com.lygin.beekeepersshopapi.services.impl;

import com.lygin.beekeepersshopapi.entity.Category;
import com.lygin.beekeepersshopapi.repositories.CategoryRepository;
import com.lygin.beekeepersshopapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
