package spring.hackerthon.post.dto;

import lombok.Builder;

@Builder
public record VoteReq(
        long postPk,
        String opinion
) {
}
