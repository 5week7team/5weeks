package com.sparta.project.controller;

import com.sparta.project.dto.request.CommentRequestDto;
import com.sparta.project.dto.response.CommentResponseDto;
import com.sparta.project.dto.response.ResponseDto;
import com.sparta.project.entity.UserDetailsImpl;
import com.sparta.project.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Refresh-Token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })
    //댓글 쓰기
    @PostMapping( "/api/auth/comment")
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto,
                                        HttpServletRequest request) {
        return commentService.createComment(requestDto, request);
    }

    @GetMapping("/api/comment/{id}")
    public ResponseDto<?> getAllComments(@PathVariable Long id) {

        return commentService.getAllCommentsByPost(id);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Refresh-Token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })
    @PutMapping( "/api/auth/comment/{id}")
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto,
                                        HttpServletRequest request) {
        return commentService.updateComment(id, requestDto, request);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Refresh-Token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })
    @DeleteMapping( "/api/auth/comment/{id}")
    public ResponseDto<?> deleteComment(@PathVariable Long id,
                                        HttpServletRequest request) {
        return commentService.deleteComment(id, request);
    }
}