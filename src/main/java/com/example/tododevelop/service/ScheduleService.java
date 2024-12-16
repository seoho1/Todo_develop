package com.example.tododevelop.service;


import com.example.tododevelop.dto.schedule.SchedulePageResponseDto;
import com.example.tododevelop.dto.schedule.ScheduleResponseDto;
import com.example.tododevelop.entity.Member;
import com.example.tododevelop.entity.Schedule;
import com.example.tododevelop.repository.MemberRepository;
import com.example.tododevelop.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    @PersistenceContext
    private EntityManager em;

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;



    public ScheduleResponseDto createSchedule(String title, String contents, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);


        Schedule schedule = new Schedule(title, contents);
        schedule.setMember(findMember);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getMember().getUsername());

    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository
                .findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents) {
        Schedule schedule = em.find(Schedule.class, id);

        if(schedule == null) {
            throw new RuntimeException("schedule not found");
        }

        schedule.update(title, contents);

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getMember().getUsername()
        );
    }
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Transactional
    public Page<SchedulePageResponseDto> getAllSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());

        Page<Schedule> allSchedules = scheduleRepository.findAll(pageable);

        return allSchedules.map(schedule -> new SchedulePageResponseDto(
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getComments().size(),
                schedule.getCreatedAt().toString(),
                schedule.getUpdatedAt().toString(),
                schedule.getMember().getUsername()
        ));
    }
}
