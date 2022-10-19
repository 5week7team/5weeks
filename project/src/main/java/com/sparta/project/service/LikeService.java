package com.sparta.project.service;

import com.sparta.project.dto.response.ResponseDto;
import com.sparta.project.entity.Member;
import com.sparta.project.entity.Post;
import com.sparta.project.entity.Postlike;
import com.sparta.project.jwt.TokenProvider;
import com.sparta.project.repository.CommentRepository;
import com.sparta.project.repository.MemberRepository;
import com.sparta.project.repository.PostRepository;
import com.sparta.project.repository.PostlikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;
    private final PostlikeRepository postlikeRepository;

    @Transactional
    public ResponseDto<?> likePost(Long id, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }
        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }
        Post post = isPresentPost(id);
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }


        List<Postlike> postlikes=postlikeRepository.findAllByPost(post);
        boolean check=false;
        for(Postlike postlike:postlikes)
        {
            if(postlike.getMember().equals(member))
            {
                check=true;
                System.out.println("이미 좋아요한 게시물입니다.");
                post.pushDislike();
                postlikeRepository.delete(postlike);
                break;
            }
        }
        if(check==false)
        {
            post.pushLike();
            System.out.println("좋아요.");
            Postlike postlike= Postlike.builder()
                    .member(member)
                    .post(post)
                    .build();
            postlikeRepository.save(postlike);
        }

        return ResponseDto.success("Push 'like' button");
    }
    @Transactional
    public Post isPresentPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }
    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
}