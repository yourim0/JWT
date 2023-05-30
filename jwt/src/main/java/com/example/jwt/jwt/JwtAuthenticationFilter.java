package com.example.jwt.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends GenericFilterBean {
    //클라이언트 요청시 jwt 인증을 하기위해 설치하는 커스텀필터. UsernamePasswordAuthenticationFilter 이전에 실행.
    //Username + Password를 통한 인증을 Jwt를 통해 수행

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //Request Header에서 토큰 정보 추출
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override  //GenericFilterBean 메소드 override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //1. Request Header 에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        //2. validateToken 으로 토큰 유효성검사
        if(token != null & jwtTokenProvider.validateToken(token)){ //token이 null이 아니면서 true 인경우)
            //토큰이 유효할 경우 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request,response);
    }


}
