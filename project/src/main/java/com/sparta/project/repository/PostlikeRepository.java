package com.sparta.project.repository;

import com.sparta.project.entity.Post;
import com.sparta.project.entity.Postlike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostlikeRepository extends JpaRepository<Postlike, Long> {

    List<Postlike> findAllByPost(Post post);

}