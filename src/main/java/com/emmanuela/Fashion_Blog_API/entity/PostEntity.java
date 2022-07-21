package com.emmanuela.Fashion_Blog_API.entity;

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
    @CreationTimestamp
    private LocalDateTime modifiedDate;
//    @CreationTimestamp
//    private LocalDateTime deletedDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private UserEntity user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonIgnore
    private CategoryEntity category;
    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikeEntity> likedItems;

    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

}
