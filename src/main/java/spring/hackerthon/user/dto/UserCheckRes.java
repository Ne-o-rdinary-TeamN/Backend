package spring.hackerthon.user.dto;

import lombok.Builder;

@Builder
public record UserCheckRes(
        long userPk,
        String name,
        String userId,
        long createdCnt,
        long joinedCnt
) {
}
