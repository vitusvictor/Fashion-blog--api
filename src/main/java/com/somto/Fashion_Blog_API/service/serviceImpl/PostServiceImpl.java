package com.somto.Fashion_Blog_API.service.serviceImpl;

import com.somto.Fashion_Blog_API.dtos.PostDto;
import com.somto.Fashion_Blog_API.entity.CategoryEntity;
import com.somto.Fashion_Blog_API.entity.PostEntity;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.enums.UserRole;
import com.somto.Fashion_Blog_API.exceptions.*;
import com.somto.Fashion_Blog_API.exceptions.CategoryNotFoundException;
import com.somto.Fashion_Blog_API.exceptions.EmptyListException;
import com.somto.Fashion_Blog_API.exceptions.PermissionDeniedException;
import com.somto.Fashion_Blog_API.exceptions.PostsNotFoundException;
import com.somto.Fashion_Blog_API.repository.CategoryRepository;
import com.somto.Fashion_Blog_API.repository.CommentRepository;
import com.somto.Fashion_Blog_API.repository.PostRepository;
import com.somto.Fashion_Blog_API.service.PostService;
import com.somto.Fashion_Blog_API.service.UserService;
import com.somto.Fashion_Blog_API.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final HttpSession httpSession;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final SessionUtils sessionUtils;



    @Override
    public void createPost(PostDto postDto, Long categoryId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

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

//    @Override
//    public Long getUserLoggedIn() {
//        Long userId = (Long) httpSession.getAttribute("user_id");
//        if(userId == null){
//            throw new UserNotFoundException("Please log in to perform this operation");
//        }
//        return userId;
//    }

    @Override
    public List<PostEntity> viewAllPosts() {
        List<PostEntity> postEntityList = postRepository.findAll();
        if(postEntityList.isEmpty()) throw new EmptyListException("You have not created any post");
        return postEntityList;
    }

    @Override
    public List<PostDto> viewAllPostsByCategory(Long categoryId) {

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));

        List<PostEntity> posts = category.getPosts();
        List<PostDto> postDtos = new ArrayList<>();

        for(PostEntity postEntity : posts){
            PostDto userPostDto = new PostDto();
            BeanUtils.copyProperties(postEntity, userPostDto);

//            PostDto userPostDto = PostDto.builder()
//                    .title(postEntity.getTitle())
//                    .description(postEntity.getDescription())
//                    .comments(postEntity.getComments().size())
//                    .likes(postEntity.getLikedItems().size())
//                    .build();

            postDtos.add(userPostDto);
        }

        return postDtos;
    }

    @Override
    public PostEntity viewPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
    }

    @Override
    public PostEntity updatePost(Long postId, PostDto postDto) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
        if(Objects.nonNull(postDto.getTitle()) && !"".equalsIgnoreCase(postDto.getTitle()))
            BeanUtils.copyProperties(postDto, postEntity);
        if(Objects.nonNull(postDto.getDescription()) && !"".equalsIgnoreCase(postDto.getDescription()))
            BeanUtils.copyProperties(postDto, postEntity);
        return postRepository.save(postEntity);
    }

    @Override
    public void deletePost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
        postRepository.delete(post);
    }


}
