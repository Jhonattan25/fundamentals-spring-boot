package com.fundamentals.springboot.fundamentals.repository;

import com.fundamentals.springboot.fundamentals.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
