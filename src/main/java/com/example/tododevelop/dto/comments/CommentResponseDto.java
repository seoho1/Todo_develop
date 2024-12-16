package com.example.tododevelop.dto.comments;


import com.example.tododevelop.dto.schedule.ScheduleResponseDto;
import com.example.tododevelop.entity.Comment;
import com.example.tododevelop.entity.Schedule;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private final Long commentId;
    @NotNull
    private final String comment;

    public static CommentResponseDto toDto(Comment comment){
        return new CommentResponseDto(comment.getId(), comment.getComment());
    }
}
