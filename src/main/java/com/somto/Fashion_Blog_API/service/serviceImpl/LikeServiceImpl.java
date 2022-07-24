package com.somto.Fashion_Blog_API.service.serviceImpl;

import com.somto.Fashion_Blog_API.entity.LikeEntity;
import com.somto.Fashion_Blog_API.entity.PostEntity;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.exceptions.AlreadyLikedException;
import com.somto.Fashion_Blog_API.exceptions.PostsNotFoundException;
import com.somto.Fashion_Blog_API.exceptions.UserNotFoundException;
import com.somto.Fashion_Blog_API.repository.LikeRepository;
import com.somto.Fashion_Blog_API.repository.PostRepository;
import com.somto.Fashion_Blog_API.service.LikeService;
import com.somto.Fashion_Blog_API.service.UserService;
import com.somto.Fashion_Blog_API.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final HttpSession httpSession;
    private final UserService userService;
    private final PostRepository postRepository;

    private final SessionUtils sessionUtils;

    @Override
    public String  likeAPost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(user == null) throw new UserNotFoundException("You are not logged in");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        if(likeRepository.likeEntityExistByUserAndPost(user, postEntity))
            throw new AlreadyLikedException("You have liked this post before");

        LikeEntity likeEntity = LikeEntity.builder()
                .posts(postEntity)
                .user(user)
                .timeLiked(LocalDateTime.now())
                .build();


        postEntity.getLikedItems().add(likeEntity);
        postRepository.save(postEntity);

        return "Post liked";
    }

    @Override
    public int totalNumberOfLikesPerPost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()-> new PostsNotFoundException("post not found"));

        return post.getLikedItems().size();
    }

    @Override
    @Transactional
    public String unlikePost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(user == null) throw new UserNotFoundException("You are not logged in");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        likeRepository.deleteLikeEntityByUserAndPosts(user, postEntity);
        return "Post unliked";
    }
}
