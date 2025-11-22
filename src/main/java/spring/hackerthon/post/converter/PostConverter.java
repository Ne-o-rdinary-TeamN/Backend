package spring.hackerthon.post.converter;

import spring.hackerthon.post.domain.Hashtag;
import spring.hackerthon.news.dto.HotNews;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostResponseDTO;

import java.util.List;

public class PostConverter {

    public static PostResponseDTO.SinglePostViewResultDTO toSinglePostViewResultDTO(Post post) {
        return PostResponseDTO.SinglePostViewResultDTO.builder()
                .postName(post.getTitle())
                .hashtags(post.getHashtags().stream()
                        .map(Hashtag::getName).toList())
                .agreeCount(post.getAgreeCount())
                .disagreeCount(post.getDisagreeCount())
                .agreeRate(post.getAgreeRate())
                .disagreeRate(post.getDisagreeRate())
                .build();
    }

    public static PostResponseDTO.TotalPostViewResultDTO toTotalPostViewResultDTO(List<PostResponseDTO.SinglePostViewResultDTO> singlePostViewResultDTOList) {
        return PostResponseDTO.TotalPostViewResultDTO.builder()
                .singlePostViewResultDTOList(singlePostViewResultDTOList)
                .build();
    }

    public static PostResponseDTO.PostCreateResponseDTO toPostCreateResponseDTO(Post post) {
        return PostResponseDTO.PostCreateResponseDTO.builder()
                .postPk(post.getPostPk())
                .title(post.getTitle())
                .build();
    }
    public static PostResponseDTO.PostListDTO toPostListDTO(Post post) {
        List<String> hashtags = post.getHashtags().stream()
                .map(h -> h.getName())
                .toList();
        return PostResponseDTO.PostListDTO.builder()
                .agree(post.getAgree())
                .disagree(post.getDisagree())
                .postPk(post.getPostPk())
                .title(post.getTitle())
                .category(post.getCategory())
                .agreeCount(post.getAgreeCount())
                .disagreeCount(post.getDisagreeCount())
                .agreeRate(post.getAgreeRate())
                .disagreeRate(post.getDisagreeRate())
                .totalCount(post.getTotalCount())
                .hashtags(hashtags)
                .build();
    }


        public static PostResponseDTO.PostDetailDTO toPostDetailDTO(
                boolean participated,
                Post post,
                List<String> hashtags,
                List<HotNews> news

        ) {
            return PostResponseDTO.PostDetailDTO.builder()
                    .participated(participated)
                    .agree(post.getAgree())
                    .disagree(post.getDisagree())
                    .postPk(post.getPostPk())
                    .title(post.getTitle())
                    .category(post.getCategory())
                    .agreeCount(post.getAgreeCount())
                    .disagreeCount(post.getDisagreeCount())
                    .agreeRate(post.getAgreeRate())
                    .disagreeRate(post.getDisagreeRate())
                    .totalCount(post.getTotalCount())
                    .commentCount(post.getCommentCount())
                    .hashtags(hashtags)
                    .news(news)
                    .build();
        }
    }
