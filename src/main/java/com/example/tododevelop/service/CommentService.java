package com.example.tododevelop.service;


import com.example.tododevelop.dto.comments.CommentResponseDto;
import com.example.tododevelop.dto.comments.CreateCommentRequestDto;
import com.example.tododevelop.dto.schedule.CreateScheduleRequestDto;
import com.example.tododevelop.entity.Comment;
import com.example.tododevelop.repository.CommentRepository;
import com.example.tododevelop.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    @PersistenceContext
    private EntityManager em;
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(String comment) {

        Comment createdComment = new Comment(comment);

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
}
