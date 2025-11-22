package spring.hackerthon.global.security;

import java.util.Collection;


public record JwtPrincipal(
        Long userPk,
        String userId,
        String userName,
        Collection<String> roles
) { }
