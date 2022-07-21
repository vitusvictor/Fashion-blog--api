package com.emmanuela.Fashion_Blog_API.entity;

import com.emmanuela.Fashion_Blog_API.enums.UserRole;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_table")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(unique = true, nullable = false, length = 30)
    private String email;
    @Column(nullable = false, length = 15)
    private String password;
    @Column(unique = true, nullable = false)
    private String userName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();

}

//BeanUtils.copyProperties(taskdto, new task);