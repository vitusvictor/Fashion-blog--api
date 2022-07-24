package com.somto.Fashion_Blog_API.service;

import com.somto.Fashion_Blog_API.dtos.CommentDto;
import com.somto.Fashion_Blog_API.entity.CommentEntity;
;import java.util.List;


public interface CommentService {
    void makeComment(Long postId, CommentEntity commentEntity);
    //Long getUserLoggedIn();

    int totalNumberOfComments(Long postId);


    CommentEntity updateComment(Long commentId, CommentDto commentDto);

    String deleteComment(Long commentId);

    CommentEntity viewCommentById(Long postId);

    List<CommentDto> viewAllCommentsByPost(Long postId);
}
