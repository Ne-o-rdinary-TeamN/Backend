package spring.hackerthon.post.dto;

import lombok.*;
import spring.hackerthon.post.domain.Category;

import java.util.List;

public class PostRequestDTO {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostCreateRequestDTO {
        private String title;
        private String newsUrl;
        private String agree;
        private String disagree;
        private Category category;
        private List<String> hashtags;
    }
}
