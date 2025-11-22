package spring.hackerthon.global.security;

import java.util.Collection;


public record JwtPrincipal(
        Long userPk,
        String userId,
        String userName,
        String nickname,
        Long age,
        Collection<String> roles
) { }
