package com.somto.Fashion_Blog_API.dtos;

import lombok.*;

import java.util.List;

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

    private List<Long> categoryIds;
}
