package com.sparta.project.entity;

import com.sparta.project.dto.request.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Comment extends TimeStamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id", nullable = false)
    private Long id;        // 댓글 고유 id

    // OneToMany
    @Column (name = "postId", nullable = false)
    private Long postId;    // 댓글이 달린 게시글 id

    @Column (name = "comment", nullable = false)
    private String comment;  // 댓글

    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    private Member member;  // 댓글 작성자

    public Comment(CommentRequestDto commentRequestDto, Member member) {
        this.postId = commentRequestDto.getPostId();
        this.comment = commentRequestDto.getComment();
        this.member = member;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.postId = commentRequestDto.getPostId();
        this.comment = commentRequestDto.getComment();
    }
}