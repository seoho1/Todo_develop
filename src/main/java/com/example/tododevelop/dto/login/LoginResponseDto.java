package com.example.tododevelop.dto.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginResponseDto {

    private final Long id;

    private final String email;

    private final String username;

}
