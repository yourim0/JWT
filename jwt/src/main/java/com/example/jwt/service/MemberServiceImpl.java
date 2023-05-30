package com.example.jwt.service;

import com.example.jwt.domain.Member;
import com.example.jwt.domain.TokenInfo;
import com.example.jwt.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenInfo login(String email, String password) {
        //1. login id/pw 기반으로 authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false?????
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);

        //2. 실제 검증(사용자 비밀번호 체크)
        //authenticate 메서드 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3. 인증정보 기반으로 jwt 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
}
