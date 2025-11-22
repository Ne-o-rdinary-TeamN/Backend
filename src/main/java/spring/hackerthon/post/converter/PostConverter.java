package spring.hackerthon.post.converter;

import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostResponseDTO;

public class PostConverter {

    public static PostResponseDTO.PostCreateResponseDTO toPostCreateResponseDTO(Post post) {
        return PostResponseDTO.PostCreateResponseDTO.builder()
                .postPk(post.getPostPk())
                .title(post.getTitle())
                .build();
    }
    public static PostResponseDTO.PostListDTO toPostListDTO(Post post) {
        return PostResponseDTO.PostListDTO.builder()
                .postPk(post.getPostPk())
                .title(post.getTitle())
                .category(post.getCategory())
                .agreeCount(post.getAgreeCount())
                .disagreeCount(post.getDisagreeCount())
                .agreeRate(post.getAgreeRate())
                .disagreeRate(post.getDisagreeRate())
                .totalCount(post.getTotalCount())
                .build();
    }

}
