package com.example.jwt.service;

import com.example.jwt.domain.Member;
import com.example.jwt.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //유저정보 찾아
        Member member = memberMapper.getUser(email);
        System.out.println("get User ------");
        return null;
    }

    //해당하는 user 데이터 존재 시 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member){
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                //.roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}
