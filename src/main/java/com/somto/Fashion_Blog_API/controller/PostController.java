package com.somto.Fashion_Blog_API.controller;

import com.somto.Fashion_Blog_API.dtos.CommentDto;
import com.somto.Fashion_Blog_API.dtos.PostDto;
import com.somto.Fashion_Blog_API.entity.PostEntity;
import com.somto.Fashion_Blog_API.service.CommentService;
import com.somto.Fashion_Blog_API.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/adminPosts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/createPostBycategoryId/{id}")
    public String createNewPost(@PathVariable("id") Long categoryId, @RequestBody PostDto postDto){
        postService.createPost(postDto, categoryId);
        return "Post created";
    }

    @GetMapping("/viewAllPosts")
    public ResponseEntity<List<PostEntity>> viewAllPosts(){
        return ResponseEntity.ok(postService.viewAllPosts());
    }

    @GetMapping("/viewPostById/{id}")
    public ResponseEntity<PostEntity> viewPostById(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(postService.viewPostById(postId));
    }

    @GetMapping("/viewPostByCategory/{id}")
    public ResponseEntity<List<PostDto>> viewAllPostByCategory(@PathVariable ("id") Long categoryId){
        return ResponseEntity.ok(postService.viewAllPostsByCategory(categoryId));
    }
    @GetMapping("/viewAllCommentsInAPost/{id}")
    public ResponseEntity<List<CommentDto>> viewAllCommentByPost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(commentService.viewAllcommentByPost(postId));
    }

    @PostMapping("/updatePost/{id}")
    public ResponseEntity<PostEntity> updatePost(@PathVariable ("id") Long postId, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @DeleteMapping("/deletepost/{id}")
    public String deletePost(@PathVariable ("id") Long postId){
        postService.deletePost(postId);
        return "Post deletion successful";
    }

}
