package com.example.tododevelop.service;

import com.example.tododevelop.dto.LoginResponseDto;
import com.example.tododevelop.dto.MemberResponseDto;
import com.example.tododevelop.dto.SignUpResponseDto;
import com.example.tododevelop.entity.Member;
import com.example.tododevelop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    @PersistenceContext
    private EntityManager em;

    private final MemberRepository memberRepository;


    public SignUpResponseDto signUp(String username, String password, String email){

        Member member = new Member(username, password, email);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());

    }

    public MemberResponseDto findById(Long id) {

        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);

        return new MemberResponseDto(findMember.getUsername(), findMember.getEmail());

    }

    @Transactional
    public MemberResponseDto updateMember(Long id, String username, String email) {
        Member member = em.find(Member.class, id);

        if(member == null) {
            throw new RuntimeException("Member not found");
        }

        member.update(username, email);

        return new MemberResponseDto(member.getUsername(), member.getEmail());

    }

    public void deleteById(Long id) {

        memberRepository.deleteById(id);

    }

    public LoginResponseDto login(String email, String password) {
        Member findMember = memberRepository.findMemberByEmailOrElseThrow(email);

        return new LoginResponseDto(findMember.getUsername(), findMember.getEmail());
    }
}
