package com.somto.Fashion_Blog_API.controller;

import com.somto.Fashion_Blog_API.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usersLikes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/likeAPost/{id}")
    public ResponseEntity<String> likeAPost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(likeService.likeAPost(postId));
    }

    @GetMapping("/noOfLikes/{id}")
    public int totalNumberOfLikes(@PathVariable ("id") Long postId){
        return likeService.totalNumberOfLikesPerPost(postId);
    }

    @DeleteMapping("/unlikePost/{id}")
    public ResponseEntity<String> unlikePost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(likeService.unlikePost(postId));
    }
}
