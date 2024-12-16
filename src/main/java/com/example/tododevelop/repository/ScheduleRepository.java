package com.example.tododevelop.repository;

import com.example.tododevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findMemberById(Long id);

    default Schedule findMemberByIdOrElseThrow(Long id){
        return findMemberById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist email" + id));
    }
}
