package com.example.tododevelop.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SchedulePageResponseDto {

    private final String title;
    private final String contents;
    private final int commentCount;
    private final String createdAt;
    private final String updatedAt;
    private final String username;

}
