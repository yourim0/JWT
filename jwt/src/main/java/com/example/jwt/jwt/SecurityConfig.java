package com.example.jwt.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //설정파일을 만들기 위한 어노테이션
@EnableWebSecurity //SpringSecurityFilterChain이 자동으로 포함, 빈 등록을 통해 Security를 설정
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable() //restapi 이므로 basic auth, csrf보안을 사용하지 않는다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JWT를 사용하기 때문에 세션을 사용하지 않는다.
                .and()
                .authorizeRequests()
                .antMatchers("/members/login").permitAll()//해당 api대해서는 모든 요청을 허가.
                .antMatchers("/members/test").hasRole("USER") //USER 권한이 있어야 요청할 수 있다.
                .anyRequest().authenticated() //외의 모든 요청에 대해서는 인증이 필요하다.
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                //JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다는 설정
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
