package com.example.tododevelop.dto;

import com.example.tododevelop.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }
}
