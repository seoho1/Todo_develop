package com.example.tododevelop.service;


import com.example.tododevelop.dto.comments.CommentResponseDto;
import com.example.tododevelop.dto.comments.CreateCommentRequestDto;
import com.example.tododevelop.dto.schedule.CreateScheduleRequestDto;
import com.example.tododevelop.entity.Comment;
import com.example.tododevelop.repository.CommentRepository;
import com.example.tododevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(String comment) {

        Comment createdComment = new Comment(comment);

        commentRepository.save(createdComment);

        return new CommentResponseDto(createdComment.getId(), createdComment.getComment());
    }
}
