package com.somto.Fashion_Blog_API.repository;

import com.somto.Fashion_Blog_API.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
