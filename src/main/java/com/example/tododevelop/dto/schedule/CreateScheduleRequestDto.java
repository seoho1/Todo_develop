package com.example.tododevelop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateScheduleRequestDto {

    @NotBlank(message = "제목을 필수 입력 값입니다.")
    private final String title;

    @NotBlank(message = "본문에 내용이 없습니다.")
    private final String contents;

    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    private final String username;

}
