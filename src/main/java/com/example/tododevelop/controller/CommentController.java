package com.example.tododevelop.controller;


import com.example.tododevelop.dto.comments.CommentResponseDto;
import com.example.tododevelop.dto.comments.CreateCommentRequestDto;
import com.example.tododevelop.dto.schedule.CreateScheduleRequestDto;
import com.example.tododevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> CreateComment(@RequestBody CreateCommentRequestDto requestDto) {

        CommentResponseDto comment = commentService.createComment(requestDto.getComment());

        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }

}
