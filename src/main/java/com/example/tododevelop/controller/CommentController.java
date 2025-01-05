package com.example.tododevelop.controller;


import com.example.tododevelop.dto.comments.CommentResponseDto;
import com.example.tododevelop.dto.comments.CreateCommentRequestDto;
import com.example.tododevelop.service.CommentService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}/comments")
    public ResponseEntity<CommentResponseDto> CreateComment(
            @PathVariable Long scheduleId,
            HttpSession session,
            @RequestBody CreateCommentRequestDto requestDto) {

        Long sessionKey = (Long) session.getAttribute("sessionKey");

        CommentResponseDto comment = commentService.createComment(scheduleId, sessionKey, requestDto.getComment());

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

    @DeleteMapping("/{scheduleId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
