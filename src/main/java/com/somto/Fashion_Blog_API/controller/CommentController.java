package com.somto.Fashion_Blog_API.controller;

import com.somto.Fashion_Blog_API.dtos.CommentDto;
import com.somto.Fashion_Blog_API.entity.CommentEntity;
import com.somto.Fashion_Blog_API.service.CommentService;
import com.somto.Fashion_Blog_API.service.PostService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usersComments")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/makeComment/{id}")
    public String makeCommentToAPost(@PathVariable ("id") Long postId, @RequestBody CommentEntity commentEntity){
        commentService.makeComment(postId, commentEntity);
        return "Comment saved successfully";
    }

    @GetMapping("/numberOfComments/{id}")
    public int numberOfComments(@PathVariable ("id") Long postId){
        return commentService.totalNumberOfComments(postId);
    }


    @PatchMapping("/updateComment/{id}")
    public ResponseEntity<CommentEntity> updateComment(@PathVariable ("id") Long commentId, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(commentId, commentDto));
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable ("id") Long commentId){
        return  ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
