package com.emmanuela.Fashion_Blog_API.controller;

import com.emmanuela.Fashion_Blog_API.dtos.PostDto;
import com.emmanuela.Fashion_Blog_API.entity.PostEntity;
import com.emmanuela.Fashion_Blog_API.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/adminposts")
public class PostController {

    private final PostService postService;

    @PostMapping("/{id}")
    public String createNewPost(@PathVariable("id") Long categoryId, @RequestBody PostDto postDto){
        postService.createPost(postDto, categoryId);
        return "Post created";
    }

}
