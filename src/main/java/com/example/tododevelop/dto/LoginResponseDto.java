package com.example.tododevelop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginResponseDto {

    private final String email;

    private final String username;

}
