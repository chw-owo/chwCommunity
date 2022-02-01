package com.example.chwblog.repository;

import com.example.chwblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll();//findAllByOrderByModifiedAtDesc();
}