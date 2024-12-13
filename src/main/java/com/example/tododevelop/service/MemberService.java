package com.example.tododevelop.service;

import com.example.tododevelop.dto.SignUpResponseDto;
import com.example.tododevelop.entity.Member;
import com.example.tododevelop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, String email){

        Member member = new Member(username, password, email);

        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());

    }

}
