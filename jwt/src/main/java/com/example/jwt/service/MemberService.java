package com.example.jwt.service;

import com.example.jwt.domain.Member;
import com.example.jwt.domain.TokenInfo;

public interface MemberService {

    public TokenInfo login(String email, String password);
}
