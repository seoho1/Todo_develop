package com.example.tododevelop.dto.comments;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateCommentRequestDto {

    @NotNull
    private final String comment;

}
