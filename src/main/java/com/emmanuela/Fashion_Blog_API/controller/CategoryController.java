package com.emmanuela.Fashion_Blog_API.controller;

import com.emmanuela.Fashion_Blog_API.dtos.CategoryDto;
import com.emmanuela.Fashion_Blog_API.entity.CategoryEntity;
import com.emmanuela.Fashion_Blog_API.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categoryposts")
public class CategoryController {

    private  final CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/viewAParticularCategory/{id}")
    public ResponseEntity<CategoryEntity> viewACategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.fetchCategory(categoryId));
    }

    @GetMapping("/viewAllCategories")
    public  ResponseEntity<List<CategoryEntity>> viewAllCategories(){
        return ResponseEntity.ok(categoryService.viewCategories());
    }

    @PatchMapping("/modifyCategory/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }


    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategpry(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
