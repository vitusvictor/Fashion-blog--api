package com.emmanuela.Fashion_Blog_API.service;

import com.emmanuela.Fashion_Blog_API.dtos.CategoryDto;
import com.emmanuela.Fashion_Blog_API.entity.CategoryEntity;
import org.springframework.http.RequestEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryEntity createCategory(CategoryEntity category);

    List<CategoryEntity> viewCategories();

    CategoryEntity updateCategory(Long categoryId, CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    CategoryEntity fetchCategory(Long categoryId);
}
