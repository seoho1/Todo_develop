package com.example.tododevelop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleRequestDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private final String title;

    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private final String contents;

}
