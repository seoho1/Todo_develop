package com.example.tododevelop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateScheduleRequestDto {

    @NotBlank
    private final String title;

    @NotNull
    private final String contents;

    @NotBlank
    private final String username;

}
