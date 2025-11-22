package spring.hackerthon.user.dto;

import lombok.Builder;

@Builder
public record UserLoginRes(
        //토큰 타입 (Bearer)
        String tokenType,

        //access token
        String accessToken,

        //유효기간
        long expiresIn,

        //사용자 pk
        Long userPk
) {}
