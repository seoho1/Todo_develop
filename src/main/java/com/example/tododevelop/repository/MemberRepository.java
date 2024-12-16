package com.example.tododevelop.repository;

import com.example.tododevelop.entity.Member;
import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);

    default Member findMemberByUsernameOrElseThrow(String username) {
        return findMemberByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist username = " + username));
    }

    Optional<Member> findMemberById(Long id);

    default Member findMemberByIdOrElseThrow(Long id){
        return findMemberById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist id" + id));
    }

    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email){
        return findMemberByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dose not exist email" + email));
    }

}
