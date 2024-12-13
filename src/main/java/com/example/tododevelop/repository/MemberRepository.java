package com.example.tododevelop.repository;

import com.example.tododevelop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);

    default Member findMemberByUsernameOrElseThrow(String username) {
        return findMemberByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist username = " + username));
    }
}
