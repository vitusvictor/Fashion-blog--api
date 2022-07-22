package com.somto.Fashion_Blog_API.repository;

import com.somto.Fashion_Blog_API.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail (String email);

    Optional<UserEntity> findByEmail (String email);

}
