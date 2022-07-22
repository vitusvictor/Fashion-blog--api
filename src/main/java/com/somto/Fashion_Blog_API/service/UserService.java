package com.somto.Fashion_Blog_API.service;

import com.somto.Fashion_Blog_API.dtos.LoginDto;
import com.somto.Fashion_Blog_API.dtos.SignUpDto;
import com.somto.Fashion_Blog_API.entity.UserEntity;

public interface UserService {
    UserEntity signUp(SignUpDto signUpDto);

    UserEntity login(LoginDto loginDto);

    UserEntity findUserById(Long id);

    String logout();
}
