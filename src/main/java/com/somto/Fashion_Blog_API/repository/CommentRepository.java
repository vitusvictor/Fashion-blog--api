package com.somto.Fashion_Blog_API.repository;

import com.somto.Fashion_Blog_API.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {




}
