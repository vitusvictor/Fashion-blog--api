package com.somto.Fashion_Blog_API.service.serviceImpl;

import com.somto.Fashion_Blog_API.dtos.CommentDto;
import com.somto.Fashion_Blog_API.entity.CommentEntity;
import com.somto.Fashion_Blog_API.entity.PostEntity;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.enums.UserRole;
import com.somto.Fashion_Blog_API.exceptions.CommentNotFoundException;
import com.somto.Fashion_Blog_API.exceptions.PermissionDeniedException;
import com.somto.Fashion_Blog_API.exceptions.PostsNotFoundException;
import com.somto.Fashion_Blog_API.exceptions.UserNotFoundException;
import com.somto.Fashion_Blog_API.repository.CommentRepository;
import com.somto.Fashion_Blog_API.repository.PostRepository;
import com.somto.Fashion_Blog_API.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final HttpSession httpSession;
    private  final SessionUtils sessionUtils;

    @Override
    public void makeComment(Long postId, CommentEntity commentEntity) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        // make admin able to comment
        if(!user.getUserRole().equals(UserRole.CUSTOMER))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        CommentEntity comment = CommentEntity.builder()
                .description(commentEntity.getDescription())
                .user(user)
                .posts(postEntity)
                .build();
        commentRepository.save(comment);

        postEntity.getComments().add(comment);
        postRepository.save(postEntity);
    }

    @Override
    public int totalNumberOfComments(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()-> new PostsNotFoundException("This post is not available"));
        return post.getComments().size();
    }

    @Override
    public CommentEntity updateComment(Long commentId, CommentDto commentDto) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(!user.getUserRole().equals(UserRole.CUSTOMER))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("This comment is not available"));

        if(Objects.nonNull(commentDto.getDescription()) && !commentDto.getDescription().equals("")){
            BeanUtils.copyProperties(commentDto, comment);
        }

        return commentRepository.save(comment);
    }

    @Override
    public String deleteComment(Long commentId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if (user == null){
            throw new UserNotFoundException("You are not logged in");
        }

        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found!"));

        commentRepository.delete(comment);

        return "Comment deleted successfully!";
    }

    @Override
    public CommentEntity viewCommentById(Long postId) {
        CommentEntity comment = commentRepository.findById(postId)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found!"));

        return comment;
    }

    @Override
    public List<CommentDto> viewAllCommentsByPost(Long postId) {
        PostEntity posts = postRepository.findById(postId)
                .orElseThrow(() ->new PostsNotFoundException("This post is not available"));

        List<CommentEntity> commentEntities = posts.getComments();
        List<CommentDto> commentDtos = new ArrayList<>();

        for(CommentEntity comment : commentEntities){
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(comment, commentDto);

            commentDtos.add(commentDto);
        }
        return commentDtos;
    }
}
