package spring.hackerthon.User.dto;

public record UserSignUpReq(
        String userId,
        String password,
        String name
) {}