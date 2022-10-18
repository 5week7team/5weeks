package com.sparta.project.service;

import com.sparta.project.dto.request.LikeRequestDto;
import com.sparta.project.dto.response.ResponseDto;
import com.sparta.project.entity.PostLike;
import com.sparta.project.entity.Member;
import com.sparta.project.entity.Post;
import com.sparta.project.entity.UserDetailsImpl;
import com.sparta.project.repository.LikeRepository;
import com.sparta.project.repository.MemberRepository;
import com.sparta.project.repository.PostRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LikeService {

   private final PostRepository postRepository;

   private final MemberRepository memberRepository;

   private final LikeRepository likeRepository;
   private UserDetailsImpl userDetails;

   public LikeService(PostRepository postRepository, MemberRepository memberRepository, LikeRepository likeRepository) {
      this.postRepository = postRepository;
      this.memberRepository = memberRepository;
      this.likeRepository = likeRepository;
   }

   @Transactional
    public ResponseDto<?> createLike (LikeRequestDto requestDto, UserDetails userDetails) {
       //게시글 존재 여부
       Optional<Post> postCheck = postRepository.findById(requestDto.getPostId());
       if (postCheck.isEmpty()) {
          throw new IllegalArgumentException("게시글이 없습니다.");
       }

       //회원인지 확인
      Optional<Member> membercheck = memberRepository.findById(requestDto.getMemberId());
      if (membercheck.isEmpty()) {
         throw new IllegalArgumentException("회원이 아닙니다.");
      }
      //회원이 일치하는지 확인
//      if (!userDetails.getUsername().equals(membercheck.get().getNickname())) {
//         throw new IllegalArgumentException("일치하는 회원이 아닙니다.");
//      }

      //회원이 같은 게시글에 중복으로 눌렀는지 검사
      Optional<PostLike> optionalLike = likeRepository.findByPostAndMember (postCheck.get(), membercheck.get());
      if (optionalLike.isPresent()) {
         throw new IllegalArgumentException("이미 좋아요를 눌렀습니다.");
      }

      PostLike postLike = PostLike.builder()
              .post(postCheck.get())
              .member(membercheck.get())
              .build();
      likeRepository.save(postLike);
      return ResponseDto.success("좋아요.");
   }

    @Transactional
    public ResponseDto<?> deleteLike(LikeRequestDto requestDto, UserDetails userDetails) {

        Optional<PostLike> optionalLike = likeRepository.findByPost_IdAndMember_Id(
                requestDto.getPostId(), requestDto.getMemberId());
        //좋아요가 눌려있지 않다면
        if (optionalLike.isEmpty()) {
            throw new IllegalArgumentException("좋아요를 누르지 않으셨습니다.");
        }
        Member membercheck = optionalLike.get().getMember();
        //누른게 로그인한 사람이 아니라면
        if (!userDetails.getUsername().equals(membercheck.getNickname())) {
            throw new IllegalArgumentException("일치하는 회원이 아닙니다");
        }
        likeRepository.delete(optionalLike.get());
        return ResponseDto.success("좋아요취소.");
    }

}
