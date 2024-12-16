package com.example.tododevelop.dto.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginRequestDto {

    private final String email;

    private final String password;

}
