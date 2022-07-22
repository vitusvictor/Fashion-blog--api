package com.somto.Fashion_Blog_API.controller;

import com.somto.Fashion_Blog_API.dtos.LoginDto;
import com.somto.Fashion_Blog_API.dtos.SignUpDto;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.enums.UserRole;
import com.somto.Fashion_Blog_API.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserEntity user1;

    @BeforeEach
    void setUp() {
        user1 = UserEntity.builder()
                .userName("Emmanuela")
                .email("ella@gmail.com")
                .password("12345")
                .userRole(UserRole.CUSTOMER)
                .userId(1L)
                .build();

    }

    @Test
    @Disabled
    void userSignUp() throws Exception {
        SignUpDto signUpDto = SignUpDto.builder()
                .userName("Emmanuela")
                .email("ella@gmail.com")
                .password("12345")
                .userRole(UserRole.CUSTOMER)
                .build();

        Mockito.when(userService.signUp(signUpDto))
                .thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"email\": \"ella@gmail.com\",\n" +
                        "    \"password\": \"12345\",\n" +
                        "    \"userName\": \"Emmanuela\",\n" +
                        "    \"userRole\": \"CUSTOMER\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
//    @Disabled
        void userLogin() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .email("ella@gmail.com")
                .password("12345")
                .build();

        Mockito.when(userService.login(loginDto))
                .thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"email\": \"ella@gmail.com\",\n" +
                        "    \"password\": \"12345\"\n" +
                        "}"))
                .andExpect(status().isOk());
        }
}