package com.somto.Fashion_Blog_API.service;

public interface LikeService {
    String likeAPost(Long postId);

    int totalNumberOfLikesPerPost(Long postId);

    String unlikePost(Long postId);
    //Long getUserLoggedIn();
}
