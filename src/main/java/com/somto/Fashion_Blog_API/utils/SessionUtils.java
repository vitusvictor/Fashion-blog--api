package com.somto.Fashion_Blog_API.utils;

import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.exceptions.UserNotFoundException;
import com.somto.Fashion_Blog_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionUtils {
    private final HttpSession httpSession;

    private final UserRepository userRepository;

    public Long getLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("user_id");
        if(userId == null) {
            throw new UserNotFoundException("You are not yet logged in!");
        }
        return userId;
    }

    public UserEntity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found!"));
    }
}
