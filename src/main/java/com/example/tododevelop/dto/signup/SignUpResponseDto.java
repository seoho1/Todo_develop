package com.example.tododevelop.dto.signup;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String username;

    private final String email;


}
