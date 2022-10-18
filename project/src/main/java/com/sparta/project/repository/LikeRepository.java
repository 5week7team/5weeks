package com.sparta.project.repository;

import com.sparta.project.entity.PostLike;
import com.sparta.project.entity.Member;
import com.sparta.project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostAndMember(Post post, Member member);
    Optional<PostLike> findByPost_IdAndMember_Id(Long post_id, Long member_id);
}
