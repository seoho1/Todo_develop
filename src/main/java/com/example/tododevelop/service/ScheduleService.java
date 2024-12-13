package com.example.tododevelop.service;


import com.example.tododevelop.dto.ScheduleResponseDto;
import com.example.tododevelop.entity.Member;
import com.example.tododevelop.entity.Schedule;
import com.example.tododevelop.repository.MemberRepository;
import com.example.tododevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto createSchedule(String title, String contents, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);


        Schedule schedule = new Schedule(title, contents);
        schedule.setMember(findMember);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());

    }
}
