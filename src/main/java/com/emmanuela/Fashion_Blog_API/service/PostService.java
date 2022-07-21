package com.emmanuela.Fashion_Blog_API.service;

import com.emmanuela.Fashion_Blog_API.dtos.PostDto;
import com.emmanuela.Fashion_Blog_API.entity.PostEntity;
import org.springframework.http.ResponseEntity;

public interface PostService {
    void createPost(PostDto postDto, Long categoryId);

    Long getUserLoggedIn();
}
