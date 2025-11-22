package spring.hackerthon.comment.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CommentsListRes(
        List<Comments> list
) {
}
