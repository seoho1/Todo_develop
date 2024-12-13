package com.example.tododevelop.controller;


import com.example.tododevelop.dto.CreateScheduleRequestDto;
import com.example.tododevelop.dto.ScheduleResponseDto;
import com.example.tododevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto schedule = scheduleService.createSchedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getUsername()
        );
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }


}
