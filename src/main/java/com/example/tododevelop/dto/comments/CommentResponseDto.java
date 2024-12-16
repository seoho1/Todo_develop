package com.example.tododevelop.dto.comments;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private final Long commentId;
    @NotNull
    private final String comment;
}
