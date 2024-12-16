package com.example.tododevelop.service;

import com.example.tododevelop.config.PasswordEncoder;
import com.example.tododevelop.dto.LoginResponseDto;
import com.example.tododevelop.dto.MemberResponseDto;
import com.example.tododevelop.dto.SignUpResponseDto;
import com.example.tododevelop.entity.Member;
import com.example.tododevelop.exception.InvalidPasswordException;
import com.example.tododevelop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService {

    @PersistenceContext
    private EntityManager em;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    public SignUpResponseDto signUp(String username, String password, String email){

        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(username, encodedPassword, email);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());

    }

    public MemberResponseDto findById(Long id) {

        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);

        return new MemberResponseDto(id, findMember.getUsername(), findMember.getEmail());

    }

    @Transactional
    public MemberResponseDto updateMember(Long id, String username, String email) {
        Member member = em.find(Member.class, id);

        if(member == null) {
            throw new RuntimeException("Member not found");
        }

        member.update(username, email);

        return new MemberResponseDto(id, member.getUsername(), member.getEmail());

    }

    public void deleteById(Long id) {

        memberRepository.deleteById(id);

    }

    public LoginResponseDto login(String email, String password) {
        Member findMember = memberRepository.findMemberByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다");
        }

        return new LoginResponseDto(findMember.getUsername(), findMember.getEmail());
    }
}
