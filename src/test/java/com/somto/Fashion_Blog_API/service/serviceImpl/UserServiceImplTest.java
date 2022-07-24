package com.somto.Fashion_Blog_API.service.serviceImpl;

import com.somto.Fashion_Blog_API.dtos.LoginDto;
import com.somto.Fashion_Blog_API.dtos.SignUpDto;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.enums.UserRole;
import com.somto.Fashion_Blog_API.repository.UserRepository;
import com.somto.Fashion_Blog_API.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private HttpSession httpSession;

    private UserEntity userEntity;

    private SignUpDto signUpDto;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .userName("Somto")
                .email("somto@gmail.com")
                .password("1234")
                .userRole(UserRole.CUSTOMER)
                .build();

        signUpDto = SignUpDto.builder()
                .userName("Somto")
                .email("somto@gmail.com")
                .password("1234")
                .userRole(UserRole.CUSTOMER)
                .build();

        httpSession.setAttribute("user_id", userEntity.getUserId());
        httpSession.setAttribute("Permission", "UserEntity");
    }

    @Test
    void signUp() {
        userService.signUp(signUpDto);
        verify(userRepository).save(userEntity);
    }

    @Test
    void login(){
        LoginDto loginDto = LoginDto.builder()
                .email("somto@gmail.com")
                .password("1234")
                .build();

        userService.login(loginDto);
        verify(userRepository).findByEmail(loginDto.getEmail());
    }

    @Test
    void logout(){
        userService.logout();
        verify(httpSession).invalidate();
    }
    @Test
    void getUser() {
        userService.findUserById(userEntity.getUserId());
        verify(userRepository).findById(userEntity.getUserId());


    }
}