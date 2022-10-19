package com.sparta.project.controller;

import com.sparta.project.dto.request.LikeRequestDto;
import com.sparta.project.dto.response.ResponseDto;
import com.sparta.project.entity.PostLike;
import com.sparta.project.entity.UserDetailsImpl;
import com.sparta.project.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    @PostMapping("/api/auth/like")
    public ResponseDto<?> createPostLike(@RequestBody LikeRequestDto requestDto
                                          ){

        return likeService.createLike(requestDto);
    }

    //좋아요 삭제
    @DeleteMapping("/api/auth/like")
    public ResponseDto<?> deletePostLike(@RequestBody LikeRequestDto requestDto){

        return likeService.deleteLike(requestDto);
    }

}