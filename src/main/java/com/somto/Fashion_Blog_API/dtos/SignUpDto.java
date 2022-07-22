package com.somto.Fashion_Blog_API.dtos;

import com.somto.Fashion_Blog_API.enums.UserRole;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String email;
    private String password;
    private String userName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
