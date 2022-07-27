package com.somto.Fashion_Blog_API.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.somto.Fashion_Blog_API.dtos.CategoryDto;
import com.somto.Fashion_Blog_API.entity.CategoryEntity;
import com.somto.Fashion_Blog_API.service.CategoryService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    /**
     * Method under test: {@link CategoryController#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryService.createCategory((CategoryEntity) any())).thenReturn(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(categoryEntity1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/categoryPosts/createCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":123,\"categoryName\":\"Category Name\",\"posts\":[]}"));
    }

    /**
     * Method under test: {@link CategoryController#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryService.updateCategory((Long) any(), (CategoryDto) any())).thenReturn(categoryEntity);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Category Name");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/categoryPosts/modifyCategory/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":123,\"categoryName\":\"Category Name\",\"posts\":[]}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategpry(Long)}
     */
    @Test
    void testDeleteCategpry() throws Exception {
        doNothing().when(categoryService).deleteCategory((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/categoryPosts/deleteCategory/{id}",
                123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CategoryController#deleteCategpry(Long)}
     */
    @Test
    void testDeleteCategpry2() throws Exception {
        doNothing().when(categoryService).deleteCategory((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/categoryPosts/deleteCategory/{id}",
                123L);
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CategoryController#viewACategory(Long)}
     */
    @Test
    void testViewACategory() throws Exception {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryService.fetchCategory((Long) any())).thenReturn(categoryEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categoryPosts/viewACategory/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"categoryId\":123,\"categoryName\":\"Category Name\",\"posts\":[]}"));
    }

    /**
     * Method under test: {@link CategoryController#viewAllCategories()}
     */
    @Test
    void testViewAllCategories() throws Exception {
        when(categoryService.viewCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/categoryPosts/viewAllCategories");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#viewAllCategories()}
     */
    @Test
    void testViewAllCategories2() throws Exception {
        when(categoryService.viewCategories()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/categoryPosts/viewAllCategories");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

