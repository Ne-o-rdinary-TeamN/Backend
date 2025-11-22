package spring.hackerthon.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostCreateRequestDTO {
        private String title;
        private String newsUrl;
        private String content;
        private String agree;
        private String disagree;
        private String category;
    }
}
