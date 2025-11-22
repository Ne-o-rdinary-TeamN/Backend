package spring.hackerthon.user.dto;

public record UserSignUpReq(
        String userId,
        String password,
        String name
) {}