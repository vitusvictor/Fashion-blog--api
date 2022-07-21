package com.emmanuela.Fashion_Blog_API.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String email;
    private String password;
}
