package com.somto.Fashion_Blog_API.repository;

import com.somto.Fashion_Blog_API.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByCategoryName(String category);


}
