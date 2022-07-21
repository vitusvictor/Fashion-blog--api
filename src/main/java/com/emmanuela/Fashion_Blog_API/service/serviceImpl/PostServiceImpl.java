package com.emmanuela.Fashion_Blog_API.service.serviceImpl;

import com.emmanuela.Fashion_Blog_API.dtos.PostDto;
import com.emmanuela.Fashion_Blog_API.entity.CategoryEntity;
import com.emmanuela.Fashion_Blog_API.entity.PostEntity;
import com.emmanuela.Fashion_Blog_API.entity.UserEntity;
import com.emmanuela.Fashion_Blog_API.enums.UserRole;
import com.emmanuela.Fashion_Blog_API.exceptions.CategoryNotFoundException;
import com.emmanuela.Fashion_Blog_API.exceptions.PermissionDeniedException;
import com.emmanuela.Fashion_Blog_API.exceptions.UserNotFoundException;
import com.emmanuela.Fashion_Blog_API.repository.CategoryRepository;
import com.emmanuela.Fashion_Blog_API.repository.PostRepository;
import com.emmanuela.Fashion_Blog_API.service.PostService;
import com.emmanuela.Fashion_Blog_API.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    public final HttpSession httpSession;
    public final UserService userService;
    public final CategoryRepository categoryRepository;

    @Override
    public void createPost(PostDto postDto, Long categoryId) {
        Long id = getUserLoggedIn();
        UserEntity user = userService.findUserById(id);

        if(!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));
        PostEntity post = PostEntity.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .category(category)
                .likedItems(new ArrayList<>())
                .comments(new ArrayList<>())
                .user(user)
                .build();
        postRepository.save(post);

        category.getPosts().add(post);
        categoryRepository.save(category);
    }

    @Override
    public Long getUserLoggedIn() {
        Long userId = (Long) httpSession.getAttribute("user_id");
        if(userId == null){
            throw new UserNotFoundException("Please log in to perform this operation");
        }
        return userId;
    }
}
