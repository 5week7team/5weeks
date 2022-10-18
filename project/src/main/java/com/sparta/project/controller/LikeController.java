package com.sparta.project.controller;

import com.sparta.project.dto.request.LikeRequestDto;
import com.sparta.project.dto.response.ResponseDto;
import com.sparta.project.repository.LikeRepository;
import com.sparta.project.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/auth/like/{id}")
    public ResponseDto<?> createLike (@RequestBody LikeRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {

        return likeService.createLike(requestDto, userDetails);
    }

    @DeleteMapping("/api/auth/like/{id}")
    public ResponseDto<?> deleteLike (@RequestBody LikeRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails) {
        return likeService.deleteLike(requestDto, userDetails);
    }

}
