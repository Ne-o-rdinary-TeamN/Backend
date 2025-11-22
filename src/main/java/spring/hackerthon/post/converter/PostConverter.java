package spring.hackerthon.post.converter;

import spring.hackerthon.post.domain.Hashtag;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostResponseDTO;

import java.util.List;

public class PostConverter {

    public static PostResponseDTO.PostCreateResponseDTO toPostCreateResponseDTO(Post post) {
        return PostResponseDTO.PostCreateResponseDTO.builder()
                .postPk(post.getPostPk())
                .title(post.getTitle())
                .build();
    }

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
}
