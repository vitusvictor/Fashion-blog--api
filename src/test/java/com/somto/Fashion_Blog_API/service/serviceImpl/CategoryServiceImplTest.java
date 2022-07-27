package com.somto.Fashion_Blog_API.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.somto.Fashion_Blog_API.dtos.CategoryDto;
import com.somto.Fashion_Blog_API.entity.CategoryEntity;
import com.somto.Fashion_Blog_API.entity.CommentEntity;
import com.somto.Fashion_Blog_API.entity.LikeEntity;
import com.somto.Fashion_Blog_API.entity.PostEntity;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.enums.UserRole;
import com.somto.Fashion_Blog_API.exceptions.CategoryAlreadyExistException;
import com.somto.Fashion_Blog_API.exceptions.CategoryNotFoundException;
import com.somto.Fashion_Blog_API.exceptions.EmptyListException;
import com.somto.Fashion_Blog_API.exceptions.PermissionDeniedException;
import com.somto.Fashion_Blog_API.repository.CategoryRepository;
import com.somto.Fashion_Blog_API.service.UserService;
import com.somto.Fashion_Blog_API.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @MockBean
    private HttpSession httpSession;

    @MockBean
    private SessionUtils sessionUtils;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryRepository.existsByCategoryName((String) any())).thenReturn(true);
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        assertThrows(CategoryAlreadyExistException.class, () -> categoryServiceImpl.createCategory(categoryEntity1));
        verify(categoryRepository).existsByCategoryName((String) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory2() {
        when(categoryRepository.existsByCategoryName((String) any()))
                .thenThrow(new CategoryNotFoundException("An error occurred"));
        when(categoryRepository.save((CategoryEntity) any())).thenThrow(new CategoryNotFoundException("An error occurred"));

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        assertThrows(CategoryNotFoundException.class, () -> categoryServiceImpl.createCategory(categoryEntity));
        verify(categoryRepository).existsByCategoryName((String) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryRepository.existsByCategoryName((String) any())).thenReturn(false);
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        assertSame(categoryEntity, categoryServiceImpl.createCategory(categoryEntity1));
        verify(categoryRepository).existsByCategoryName((String) any());
        verify(categoryRepository).save((CategoryEntity) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryRepository.existsByCategoryName((String) any())).thenReturn(true);
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.CUSTOMER);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        assertThrows(PermissionDeniedException.class, () -> categoryServiceImpl.createCategory(categoryEntity1));
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory5() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryRepository.existsByCategoryName((String) any())).thenReturn(true);
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.ADMIN);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        CategoryEntity categoryEntity1 = mock(CategoryEntity.class);
        when(categoryEntity1.getCategoryName()).thenReturn("Category Name");
        doNothing().when(categoryEntity1).setCategoryId((Long) any());
        doNothing().when(categoryEntity1).setCategoryName((String) any());
        doNothing().when(categoryEntity1).setPosts((List<PostEntity>) any());
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        assertThrows(CategoryAlreadyExistException.class, () -> categoryServiceImpl.createCategory(categoryEntity1));
        verify(categoryRepository).existsByCategoryName((String) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
        verify(categoryEntity1).getCategoryName();
        verify(categoryEntity1).setCategoryId((Long) any());
        verify(categoryEntity1).setCategoryName((String) any());
        verify(categoryEntity1).setPosts((List<PostEntity>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryEntity)}
     */
    @Test
    void testCreateCategory6() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryRepository.existsByCategoryName((String) any())).thenReturn(false);
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.ADMIN);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        CategoryEntity categoryEntity1 = mock(CategoryEntity.class);
        when(categoryEntity1.getCategoryName()).thenReturn("Category Name");
        doNothing().when(categoryEntity1).setCategoryId((Long) any());
        doNothing().when(categoryEntity1).setCategoryName((String) any());
        doNothing().when(categoryEntity1).setPosts((List<PostEntity>) any());
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        assertSame(categoryEntity, categoryServiceImpl.createCategory(categoryEntity1));
        verify(categoryRepository).existsByCategoryName((String) any());
        verify(categoryRepository).save((CategoryEntity) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
        verify(categoryEntity1, atLeast(1)).getCategoryName();
        verify(categoryEntity1).setCategoryId((Long) any());
        verify(categoryEntity1).setCategoryName((String) any());
        verify(categoryEntity1).setPosts((List<PostEntity>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#viewCategories()}
     */
    @Test
    void testViewCategories() {
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(EmptyListException.class, () -> categoryServiceImpl.viewCategories());
        verify(categoryRepository).findAll();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#viewCategories()}
     */
    @Test
    void testViewCategories2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName(" You have not created any category");
        categoryEntity.setPosts(new ArrayList<>());

        ArrayList<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(categoryEntity);
        when(categoryRepository.findAll()).thenReturn(categoryEntityList);
        List<CategoryEntity> actualViewCategoriesResult = categoryServiceImpl.viewCategories();
        assertSame(categoryEntityList, actualViewCategoriesResult);
        assertEquals(1, actualViewCategoriesResult.size());
        verify(categoryRepository).findAll();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#viewCategories()}
     */
    @Test
    void testViewCategories3() {
        when(categoryRepository.findAll()).thenThrow(new PermissionDeniedException("An error occurred"));
        assertThrows(PermissionDeniedException.class, () -> categoryServiceImpl.viewCategories());
        verify(categoryRepository).findAll();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity1);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertSame(categoryEntity1, categoryServiceImpl.updateCategory(123L, new CategoryDto("Category Name")));
        verify(categoryRepository).save((CategoryEntity) any());
        verify(categoryRepository).findById((Long) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        when(categoryRepository.save((CategoryEntity) any())).thenThrow(new PermissionDeniedException("An error occurred"));
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(PermissionDeniedException.class,
                () -> categoryServiceImpl.updateCategory(123L, new CategoryDto("Category Name")));
        verify(categoryRepository).save((CategoryEntity) any());
        verify(categoryRepository).findById((Long) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory3() {
        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        doNothing().when(categoryEntity).setCategoryId((Long) any());
        doNothing().when(categoryEntity).setCategoryName((String) any());
        doNothing().when(categoryEntity).setPosts((List<PostEntity>) any());
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity1);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertSame(categoryEntity1, categoryServiceImpl.updateCategory(123L, new CategoryDto("Category Name")));
        verify(categoryRepository).save((CategoryEntity) any());
        verify(categoryRepository).findById((Long) any());
        verify(categoryEntity).setCategoryId((Long) any());
        verify(categoryEntity, atLeast(1)).setCategoryName((String) any());
        verify(categoryEntity).setPosts((List<PostEntity>) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity);
        when(categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        CategoryEntity categoryEntity1 = mock(CategoryEntity.class);
        doNothing().when(categoryEntity1).setCategoryId((Long) any());
        doNothing().when(categoryEntity1).setCategoryName((String) any());
        doNothing().when(categoryEntity1).setPosts((List<PostEntity>) any());
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(CategoryNotFoundException.class,
                () -> categoryServiceImpl.updateCategory(123L, new CategoryDto("Category Name")));
        verify(categoryRepository).findById((Long) any());
        verify(categoryEntity1).setCategoryId((Long) any());
        verify(categoryEntity1).setCategoryName((String) any());
        verify(categoryEntity1).setPosts((List<PostEntity>) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory5() {
        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        doNothing().when(categoryEntity).setCategoryId((Long) any());
        doNothing().when(categoryEntity).setCategoryName((String) any());
        doNothing().when(categoryEntity).setPosts((List<PostEntity>) any());
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity1);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.CUSTOMER);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(PermissionDeniedException.class,
                () -> categoryServiceImpl.updateCategory(123L, new CategoryDto("Category Name")));
        verify(categoryEntity).setCategoryId((Long) any());
        verify(categoryEntity).setCategoryName((String) any());
        verify(categoryEntity).setPosts((List<PostEntity>) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory6() {
        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        doNothing().when(categoryEntity).setCategoryId((Long) any());
        doNothing().when(categoryEntity).setCategoryName((String) any());
        doNothing().when(categoryEntity).setPosts((List<PostEntity>) any());
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity1);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.ADMIN);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertSame(categoryEntity1, categoryServiceImpl.updateCategory(123L, new CategoryDto("")));
        verify(categoryRepository).save((CategoryEntity) any());
        verify(categoryRepository).findById((Long) any());
        verify(categoryEntity).setCategoryId((Long) any());
        verify(categoryEntity).setCategoryName((String) any());
        verify(categoryEntity).setPosts((List<PostEntity>) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    void testUpdateCategory7() {
        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        doNothing().when(categoryEntity).setCategoryId((Long) any());
        doNothing().when(categoryEntity).setCategoryName((String) any());
        doNothing().when(categoryEntity).setPosts((List<PostEntity>) any());
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity1);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.ADMIN);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertSame(categoryEntity1, categoryServiceImpl.updateCategory(123L, new CategoryDto()));
        verify(categoryRepository).save((CategoryEntity) any());
        verify(categoryRepository).findById((Long) any());
        verify(categoryEntity).setCategoryId((Long) any());
        verify(categoryEntity).setCategoryName((String) any());
        verify(categoryEntity).setPosts((List<PostEntity>) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(Long, CategoryDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCategory8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.somto.Fashion_Blog_API.dtos.CategoryDto.getCategoryName()" because "categoryDto" is null
        //       at com.somto.Fashion_Blog_API.service.serviceImpl.CategoryServiceImpl.updateCategory(CategoryServiceImpl.java:72)
        //   In order to prevent updateCategory(Long, CategoryDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateCategory(Long, CategoryDto).
        //   See https://diff.blue/R013 to resolve this issue.

        CategoryEntity categoryEntity = mock(CategoryEntity.class);
        doNothing().when(categoryEntity).setCategoryId((Long) any());
        doNothing().when(categoryEntity).setCategoryName((String) any());
        doNothing().when(categoryEntity).setPosts((List<PostEntity>) any());
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryId(123L);
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setPosts(new ArrayList<>());
        when(categoryRepository.save((CategoryEntity) any())).thenReturn(categoryEntity1);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.ADMIN);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        categoryServiceImpl.updateCategory(123L, null);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        doNothing().when(categoryRepository).delete((CategoryEntity) any());
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        categoryServiceImpl.deleteCategory(123L);
        verify(categoryRepository).findById((Long) any());
        verify(categoryRepository).delete((CategoryEntity) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        doThrow(new PermissionDeniedException("An error occurred")).when(categoryRepository).delete((CategoryEntity) any());
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(PermissionDeniedException.class, () -> categoryServiceImpl.deleteCategory(123L));
        verify(categoryRepository).findById((Long) any());
        verify(categoryRepository).delete((CategoryEntity) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory3() {
        doNothing().when(categoryRepository).delete((CategoryEntity) any());
        when(categoryRepository.findById((Long) any())).thenReturn(Optional.empty());

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(CategoryNotFoundException.class, () -> categoryServiceImpl.deleteCategory(123L));
        verify(categoryRepository).findById((Long) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        doNothing().when(categoryRepository).delete((CategoryEntity) any());
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.CUSTOMER);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(PermissionDeniedException.class, () -> categoryServiceImpl.deleteCategory(123L));
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#fetchCategory(Long)}
     */
    @Test
    void testFetchCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertSame(categoryEntity, categoryServiceImpl.fetchCategory(123L));
        verify(categoryRepository).findById((Long) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#fetchCategory(Long)}
     */
    @Test
    void testFetchCategory2() {
        when(categoryRepository.findById((Long) any())).thenThrow(new PermissionDeniedException("An error occurred"));

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(PermissionDeniedException.class, () -> categoryServiceImpl.fetchCategory(123L));
        verify(categoryRepository).findById((Long) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#fetchCategory(Long)}
     */
    @Test
    void testFetchCategory3() {
        when(categoryRepository.findById((Long) any())).thenReturn(Optional.empty());

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(CategoryNotFoundException.class, () -> categoryServiceImpl.fetchCategory(123L));
        verify(categoryRepository).findById((Long) any());
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#fetchCategory(Long)}
     */
    @Test
    void testFetchCategory4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(123L);
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setPosts(new ArrayList<>());
        Optional<CategoryEntity> ofResult = Optional.of(categoryEntity);
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getUserRole()).thenReturn(UserRole.CUSTOMER);
        doNothing().when(userEntity).setComments((List<CommentEntity>) any());
        doNothing().when(userEntity).setEmail((String) any());
        doNothing().when(userEntity).setLikedItems((List<LikeEntity>) any());
        doNothing().when(userEntity).setPassword((String) any());
        doNothing().when(userEntity).setPosts((List<PostEntity>) any());
        doNothing().when(userEntity).setUserId((Long) any());
        doNothing().when(userEntity).setUserName((String) any());
        doNothing().when(userEntity).setUserRole((UserRole) any());
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(sessionUtils.findUserById((Long) any())).thenReturn(userEntity);
        when(sessionUtils.getLoggedInUser()).thenReturn(1L);
        assertThrows(PermissionDeniedException.class, () -> categoryServiceImpl.fetchCategory(123L));
        verify(sessionUtils).findUserById((Long) any());
        verify(sessionUtils).getLoggedInUser();
        verify(userEntity).getUserRole();
        verify(userEntity).setComments((List<CommentEntity>) any());
        verify(userEntity).setEmail((String) any());
        verify(userEntity).setLikedItems((List<LikeEntity>) any());
        verify(userEntity).setPassword((String) any());
        verify(userEntity).setPosts((List<PostEntity>) any());
        verify(userEntity).setUserId((Long) any());
        verify(userEntity).setUserName((String) any());
        verify(userEntity).setUserRole((UserRole) any());
    }
}

