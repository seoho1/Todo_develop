package com.example.tododevelop.controller;


import com.example.tododevelop.dto.comments.CommentResponseDto;
import com.example.tododevelop.dto.comments.CreateCommentRequestDto;
import com.example.tododevelop.dto.schedule.CreateScheduleRequestDto;
import com.example.tododevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAllComment() {

        List<CommentResponseDto>  commentResponseDtoList = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestBody CreateCommentRequestDto comment) {

        CommentResponseDto commentResponseDto = commentService.updateComment(id, comment.getComment());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

}
