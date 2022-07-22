package com.somto.Fashion_Blog_API.service;

import com.somto.Fashion_Blog_API.dtos.CategoryDto;
import com.somto.Fashion_Blog_API.entity.CategoryEntity;
import java.util.List;

public interface CategoryService {
    //Long getUserLoggedIn();
    CategoryEntity createCategory(CategoryEntity category);

    List<CategoryEntity> viewCategories();

    CategoryEntity updateCategory(Long categoryId, CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    CategoryEntity fetchCategory(Long categoryId);
}
