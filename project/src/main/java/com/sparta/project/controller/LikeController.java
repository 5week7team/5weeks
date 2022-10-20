package com.sparta.project.controller;

import com.sparta.project.dto.response.ResponseDto;
import com.sparta.project.service.LikeService;
import com.sparta.project.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Refresh-Token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })

    @PostMapping("/api/auth/postlike/{id}")
    public ResponseDto<?> likePost(@PathVariable Long id,
                                   HttpServletRequest request) {
        return likeService.likePost(id, request);
    }
}