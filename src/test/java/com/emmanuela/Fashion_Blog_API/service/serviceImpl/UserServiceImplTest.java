package com.emmanuela.Fashion_Blog_API.service.serviceImpl;

import com.emmanuela.Fashion_Blog_API.dtos.LoginDto;
import com.emmanuela.Fashion_Blog_API.dtos.SignUpDto;
import com.emmanuela.Fashion_Blog_API.entity.UserEntity;
import com.emmanuela.Fashion_Blog_API.enums.UserRole;
import com.emmanuela.Fashion_Blog_API.repository.UserRepository;
import com.emmanuela.Fashion_Blog_API.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    @Disabled
    void signUp() {
        UserEntity userEntity = UserEntity.builder()
                        .userName("Emmanuela")
                        .email("ella@gmail.com")
                        .password("12345")
                        .userRole(UserRole.CUSTOMER)
                        .build();
        userRepository.save(userEntity);

        Optional<UserEntity> userEntity1 = userRepository.findByEmail("ella@gmail.com");
        assertEquals("ella@gmail.com", userEntity1.get().getEmail());

    }

    @Test
    void login(){
        LoginDto loginDto = LoginDto.builder()
                .email("ella@gmail.com")
                .password("12345")
                .build();
        assertEquals(loginDto.getEmail(), userService.login(loginDto).getEmail());
    }

}