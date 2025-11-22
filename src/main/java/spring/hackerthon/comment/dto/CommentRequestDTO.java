package spring.hackerthon.comment.dto;

import lombok.*;

public class CommentRequestDTO {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentCreateDTO {
        private String content;
    }
}
