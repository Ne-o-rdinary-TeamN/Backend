package spring.hackerthon.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.hackerthon.post.domain.Category;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostRequestDTO;
import spring.hackerthon.post.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post joinPost(PostRequestDTO.PostCreateRequestDTO request) {

        if (request.getTitle() != null && request.getNewsUrl() != null
        && request.getAgree() != null && request.getDisagree() != null) {
            Post post = Post.builder()
                    .title(request.getTitle())
                    .url(request.getNewsUrl())
                    .agree(request.getAgree())
                    .disagree(request.getDisagree())
                    .category(Category.toCategory(request.getCategory()))
                    .agreeCount(0L)
                    .disagreeCount(0L)
                    .agreeRate(0D)
                    .disagreeRate(0D)
                    .commentCount(0L)
                    .totalCount(0L)
//                .user()
                    .build();

            return postRepository.save(post);
        }
        return null;
    }
}
