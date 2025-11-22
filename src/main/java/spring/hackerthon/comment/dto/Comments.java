package spring.hackerthon.comment.dto;

import lombok.Builder;

@Builder
public record Comments(
        long commentPk,
        String userId,
        String content,
        long likes
) {
}