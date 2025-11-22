package spring.hackerthon.user.dto;

public record UserLoginReq(
        String userId,
        String password
) {}
