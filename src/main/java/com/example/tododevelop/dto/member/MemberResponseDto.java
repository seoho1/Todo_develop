package com.example.tododevelop.dto.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberResponseDto {

    private final Long id;

    private final String username;

    private final String email;


}
