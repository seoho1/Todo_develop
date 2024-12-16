package com.example.tododevelop.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateMemberRequestDto {

    private final String username;

    private final String email;

}
