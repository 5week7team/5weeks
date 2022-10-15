package com.sparta.project.service;

import com.sparta.project.dto.request.CommentRequestDto;
import com.sparta.project.dto.response.CommentResponseDto;
import com.sparta.project.entity.Comment;
import com.sparta.project.entity.Member;
import com.sparta.project.repository.CommentRepository;
import com.sparta.project.repository.MemberRepository;
import com.sparta.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private Member getUser(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("아이디를 찾을 수 없습니다."));
        return member;
    }

    public void postCheck(CommentRequestDto commentRequestDto) {
        postRepository.findById( commentRequestDto.getPostId() )
                .orElseThrow( () -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    //댓글 쓰기
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, String username) {
        Member member = getMember(username);
        postCheck(commentRequestDto);

        Comment comment = new Comment(commentRequestDto, member);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);

    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, String username) {
        Member member = getMember(membername);
        postCheck(commentRequestDto);

        Comment comment = commentRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("댓글이 없습니다."));

        if(!member.getUsername().equals(comment.getMember().getMembername())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }

        comment.update(commentRequestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    @Transactional
    public String deleteComment(Long id, String membername) {
        Member member = getMember(username);

        Comment comment = commentRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("댓글이 없습니다."));

        if(!user.getUsername().equals(comment.getUser().getUsername())){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(id);
        return "댓글이 삭제되었습니다";
    }

    //댓글 전체목록 보기
    public List<CommentResponseDto> getCommentAllOfPost(Long id) {
        List<Comment> list = commentRepository.findAllByPostId(id);
        List<CommentResponseDto> clist = new ArrayList<>();
        for (Comment c : list) {
            //CommentResponseDto commentResponseDto = new CommentResponseDto(c);
            clist.add(new CommentResponseDto(c));
        }
        return clist;
    }
}