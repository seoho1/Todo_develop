package com.example.tododevelop.service;


import com.example.tododevelop.dto.comments.CommentResponseDto;
import com.example.tododevelop.entity.Comment;
import com.example.tododevelop.entity.Member;
import com.example.tododevelop.entity.Schedule;
import com.example.tododevelop.exception.InvalidRequestException;
import com.example.tododevelop.repository.CommentRepository;
import com.example.tododevelop.repository.MemberRepository;
import com.example.tododevelop.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    @PersistenceContext
    private EntityManager em;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(Long scheduleId, Long memberId, String comment) {
        Schedule schedule = scheduleRepository.findMemberByIdOrElseThrow(scheduleId);
        Member member = memberRepository.findMemberByIdOrElseThrow(memberId);

        Comment createdComment = new Comment(comment);
        createdComment.setMember(member);
        createdComment.setSchedule(schedule);

        commentRepository.save(createdComment);

        return new CommentResponseDto(createdComment.getId(), createdComment.getComment());
    }

    public List<CommentResponseDto> findAll() {
        return commentRepository.findAll().stream().map(CommentResponseDto::toDto).toList();
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, String comment) {

        Comment findedComment = em.find(Comment.class, id);

        if(comment == null) {
            throw new RuntimeException("Comment not found");
        }

        findedComment.update(comment);

        return new CommentResponseDto(id, findedComment.getComment());
    }

    public void deleteComment(Long id) {
        commentRepository.findById(id).orElseThrow(()-> new InvalidRequestException("댓글이 존재하지 않습니다."));
        commentRepository.deleteById(id);
    }
}
