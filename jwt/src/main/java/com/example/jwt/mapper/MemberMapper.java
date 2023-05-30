package com.example.jwt.mapper;

import com.example.jwt.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member getUser(String email);
}
