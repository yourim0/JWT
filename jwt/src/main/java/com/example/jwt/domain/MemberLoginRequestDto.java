package com.example.jwt.domain;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String email;
    private String password;

}
