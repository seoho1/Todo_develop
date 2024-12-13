package com.example.tododevelop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;


}
