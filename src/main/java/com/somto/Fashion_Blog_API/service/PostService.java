package com.somto.Fashion_Blog_API.service;

import com.somto.Fashion_Blog_API.dtos.PostDto;
import com.somto.Fashion_Blog_API.entity.PostEntity;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto, Long categoryId);

    List<PostEntity> viewAllPosts();

    List<PostDto> viewAllPostsByCategory(Long categoryId);


    PostEntity viewPostById(Long postId);

    PostEntity updatePost(Long postId, PostDto postDto);

    void deletePost(Long postId);

}
