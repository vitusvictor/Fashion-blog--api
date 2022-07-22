package com.somto.Fashion_Blog_API.service.serviceImpl;

import com.somto.Fashion_Blog_API.dtos.LoginDto;
import com.somto.Fashion_Blog_API.dtos.SignUpDto;
import com.somto.Fashion_Blog_API.entity.UserEntity;
import com.somto.Fashion_Blog_API.exceptions.PasswordIncorrectException;
import com.somto.Fashion_Blog_API.exceptions.UserAlreadyExistException;
import com.somto.Fashion_Blog_API.exceptions.UserNotFoundException;
import com.somto.Fashion_Blog_API.repository.UserRepository;
import com.somto.Fashion_Blog_API.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;
    @Override
    public UserEntity signUp(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        if(userRepository.existsByEmail(email)){
            throw new UserAlreadyExistException("Users with " + email+ "already exists");
        }
        UserEntity users = new UserEntity();
        BeanUtils.copyProperties(signUpDto, users);
//        UserEntity newUser = UserEntity.builder()
//                .userName(signUpDto.getUserName())
//                .email(signUpDto.getEmail())
//                .password(signUpDto.getPassword())
//                .userRole(UserRole.CUSTOMER)
//                .build();

        return userRepository.save(users);
    }

    @Override
    public UserEntity login(LoginDto loginDto) {
        String userEmail = loginDto.getEmail();
        String userPassword = loginDto.getPassword();

        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!userEntity.getPassword().equals(userPassword)){
            throw new PasswordIncorrectException("Password Incorrect");
        }

        httpSession.setAttribute("user_id", userEntity.getUserId());
        httpSession.setAttribute("Permission", "UserEntity");
        return userEntity;
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User not found"));
    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "User Logged Out" ;
    }
}
