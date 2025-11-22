package spring.hackerthon.user.dto;

import lombok.Builder;

@Builder
public record UserSignUpRes(
        boolean success
) {}