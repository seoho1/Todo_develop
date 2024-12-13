package com.example.tododevelop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemberResponseDto {

    private final String username;

    private final String password;

    private final String email;


}
