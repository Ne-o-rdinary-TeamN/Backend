package spring.hackerthon.post.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.hackerthon.news.dto.HotNews;
import spring.hackerthon.post.domain.Category;
import spring.hackerthon.post.domain.Hashtag;
import spring.hackerthon.recommendation.domain.Recommendation;
import spring.hackerthon.user.domain.User;

import java.util.ArrayList;
import java.util.List;

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

    public static class PostDetailDTO {

        private Long postPk;
        private String title;

        private Category category;

        private Long agreeCount;
        private Long disagreeCount;
        private Double agreeRate;
        private Double disagreeRate;
        private Long totalCount;

        private Long commentCount;

        private List<String> hashtags;
        private List<HotNews> news;
    }

}
