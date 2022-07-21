package com.emmanuela.Fashion_Blog_API.dtos;

import com.emmanuela.Fashion_Blog_API.entity.PostEntity;
import com.emmanuela.Fashion_Blog_API.entity.UserEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LikeDto implements Serializable {
    private PostEntity posts;
    private UserEntity user;
}
