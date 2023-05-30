package com.example.jwt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder //객체를 생성할 수 있는 빌더를 builder() 함수를 통해 얻고 거기에 셋팅하고자 하는 값을 셋팅하고 마지막에 build()를 통해 빌더를 작동 시켜 객체를 생성
@Data
@AllArgsConstructor
public class TokenInfo { //클라이언트에 토큰을 보내기 위한 dto
    private String grantType; //jwt 인증타입
    private String accessToken;
    private String refreshToken;
}
