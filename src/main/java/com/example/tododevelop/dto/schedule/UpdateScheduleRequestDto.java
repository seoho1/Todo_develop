package com.example.tododevelop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleRequestDto {

    @NotBlank
    private final String title;

    @NotBlank
    private final String contents;

}
