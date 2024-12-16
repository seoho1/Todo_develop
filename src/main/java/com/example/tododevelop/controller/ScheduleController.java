package com.example.tododevelop.controller;


import com.example.tododevelop.dto.schedule.CreateScheduleRequestDto;
import com.example.tododevelop.dto.schedule.SchedulePageResponseDto;
import com.example.tododevelop.dto.schedule.ScheduleResponseDto;
import com.example.tododevelop.dto.schedule.UpdateScheduleRequestDto;
import com.example.tododevelop.entity.Schedule;
import com.example.tododevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto schedule = scheduleService.createSchedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getUsername()
        );
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<SchedulePageResponseDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<SchedulePageResponseDto> schedules = scheduleService.getAllSchedules(page,size);

        return ResponseEntity.ok(schedules);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody UpdateScheduleRequestDto requestDto
            )
    {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContents());

        return new ResponseEntity<>(scheduleResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
