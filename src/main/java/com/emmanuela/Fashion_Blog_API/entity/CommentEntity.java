package com.emmanuela.Fashion_Blog_API.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok .*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false, length = 10000)
    private String description;
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @CreationTimestamp
    private LocalDateTime modifiedDate;
    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private PostEntity posts;
}
