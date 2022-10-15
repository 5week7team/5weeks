package com.sparta.project.controller;

import com.sparta.project.dto.request.CommentRequestDto;
import com.sparta.project.dto.response.CommentResponseDto;
import com.sparta.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    //댓글 쓰기
    @PostMapping("/api/auth/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailImpl userDetailImpl) {
        return commentService.createComment(commentRequestDto, userDetailImpl.getMembername());
    }

    //댓글 수정
    @PostMapping("/api/auth/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailImpl userDetailImpl) throws IllegalAccessException {
        return commentService.updateComment(id, commentRequestDto, userDetailImpl.getUsername());
    }

    //댓글 삭제
    @DeleteMapping("/api/auth/comment/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailImpl userDetailImpl) {
        return commentService.deleteComment(id, userDetailImpl.getUsername());
    }


    //댓글 전체목록 보기
    @GetMapping("/api/comment/{id}")
    public List<CommentResponseDto> getCommentAllOfPost(@PathVariable Long id) {
        return commentService.getCommentAllOfPost(id);
    }
}