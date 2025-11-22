package spring.hackerthon.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    public static class SinglePostViewResultDTO {
        private String postName;
        private List<String> hashtags;
        private Long agreeCount;
        private Long disagreeCount;
        private Double agreeRate;
        private Double disagreeRate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TotalPostViewResultDTO {
        List<SinglePostViewResultDTO> singlePostViewResultDTOList;
    }
}
