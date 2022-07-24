package com.somto.Fashion_Blog_API.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "post_table")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 10000)
    private String description;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @ManyToOne(optional = false) // CORRECT: A user can have many posts, One comment can't be owned by many users
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private UserEntity user;

    @ManyToMany(mappedBy = "posts") // ManyToOne -> ManyToMany
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonIgnore
    private List<CategoryEntity> category; // made a collection

    // CORRECT: One post can have many likes, a like can't be owned by many posts
    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeEntity> likedItems;

    // CORRECT: One post can have many comments, a comment can't be owned by many posts
    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

}
