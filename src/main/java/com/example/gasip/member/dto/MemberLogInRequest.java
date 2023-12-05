package com.example.gasip.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberLogInRequest {

    private String email;

    private String password;
}
