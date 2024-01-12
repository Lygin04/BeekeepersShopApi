package com.lygin.beekeepersshopapi.services;

import com.lygin.beekeepersshopapi.dto.CategoryDto;
import com.lygin.beekeepersshopapi.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDto category);
    List<Category> getAll();
}
