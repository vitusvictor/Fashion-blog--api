package com.emmanuela.Fashion_Blog_API.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String title;
    private String description;
    private int likes;
    private int comments;
}
