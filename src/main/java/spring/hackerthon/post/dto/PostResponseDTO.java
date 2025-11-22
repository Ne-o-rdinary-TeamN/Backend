package spring.hackerthon.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.hackerthon.post.domain.Category;

public class PostResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostCreateResponseDTO {
        private Long postPk;
        private String title;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostListDTO {
        private Long postPk;
        private String title;
        private Category category;
        private Long agreeCount;
        private Long disagreeCount;
        private Double agreeRate;
        private Double disagreeRate;
        private Long totalCount;
    }

}
