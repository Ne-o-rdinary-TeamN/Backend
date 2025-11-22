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
}
