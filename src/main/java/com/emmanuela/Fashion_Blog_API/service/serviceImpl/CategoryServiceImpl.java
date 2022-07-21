package com.emmanuela.Fashion_Blog_API.service.serviceImpl;

import com.emmanuela.Fashion_Blog_API.dtos.CategoryDto;
import com.emmanuela.Fashion_Blog_API.entity.CategoryEntity;
import com.emmanuela.Fashion_Blog_API.entity.UserEntity;
import com.emmanuela.Fashion_Blog_API.enums.UserRole;
import com.emmanuela.Fashion_Blog_API.exceptions.*;
import com.emmanuela.Fashion_Blog_API.repository.CategoryRepository;
import com.emmanuela.Fashion_Blog_API.service.CategoryService;
import com.emmanuela.Fashion_Blog_API.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final HttpSession httpSession;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    @Override
    public CategoryEntity createCategory(CategoryEntity category) {
        Long id = getUserLoggedIn();
        UserEntity user = userService.findUserById(id);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        if(categoryRepository.existsByCategoryName(category.getCategoryName()))
            throw new CategoryAlreadyExistException("This category already exists");

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName(category.getCategoryName())
                .build();
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public List<CategoryEntity> viewCategories() {
        List<CategoryEntity> allCategories = categoryRepository.findAll();
        if(allCategories.isEmpty()) throw new EmptyListException(" You have not created any category");
        return allCategories;
    }

    @Override
    public CategoryEntity updateCategory(Long categoryId, CategoryDto categoryDto) {
        Long userId = getUserLoggedIn();
        UserEntity user = userService.findUserById(userId);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));
        if(Objects.nonNull(categoryDto.getCategoryName()) && !"".equalsIgnoreCase(categoryDto.getCategoryName()))
            BeanUtils.copyProperties(categoryDto, category);
            //category.setCategoryName(categoryDto.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Long userId = getUserLoggedIn();
        UserEntity user = userService.findUserById(userId);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryEntity fetchCategory(Long categoryId) {
        Long userId = getUserLoggedIn();
        UserEntity user = userService.findUserById(userId);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if(category.isPresent())
            return category.get();
        throw new CategoryNotFoundException("This category is not available");
    }

    public Long getUserLoggedIn() {
        Long userId = (Long) httpSession.getAttribute("user_id");
        if(userId == null)
            throw new UserNotFoundException("Please log in to perform this operation");
        return userId;
    }

}
