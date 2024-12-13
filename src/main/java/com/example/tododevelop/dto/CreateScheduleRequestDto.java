package com.example.tododevelop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateScheduleRequestDto {

    private final String title;

    private final String contents;

    private final String username;

}
