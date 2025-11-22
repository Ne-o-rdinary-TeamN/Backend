package spring.hackerthon.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.news.dto.HotNews;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.domain.Category;
import spring.hackerthon.post.domain.Hashtag;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.PostResponseDTO;
import spring.hackerthon.post.repository.HashtagRepository;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.post.dto.PostRequestDTO;
import spring.hackerthon.post.repository.PostRepository;
import spring.hackerthon.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    public Post joinPost(Long userPk, PostRequestDTO.PostCreateRequestDTO request) {

        User user = userRepository.findById(userPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        Post post = Post.builder()
                .title(request.getTitle())
                .url(request.getNewsUrl())
                .agree(request.getAgree())
                .disagree(request.getDisagree())
                .category(request.getCategory())
                .agreeCount(0L)
                .disagreeCount(0L)
                .agreeRate(0D)
                .disagreeRate(0D)
                .commentCount(0L)
                .totalCount(0L)
                .user(user)
                .build();

        List<Hashtag> hashtags = request.getHashtags().stream()
                .map(h -> Hashtag.builder()
                            .name(h)
                            .post(post)
                            .build()
                )
                .toList();

        post.getHashtags().addAll(hashtags);

        return postRepository.save(post);
    }

    public String getKeywords(Post post) {
        List<Hashtag> hashtags = hashtagRepository.findAllByPost(post);

        return hashtags.stream()
                .map(Hashtag::getName)
                .collect(Collectors.joining(" "));
    }

    public Page<Post> getPostList(Category category, Pageable pageable) {

        if (category == null) {
            return postRepository.findAll(pageable);
        }
        return postRepository.findAllByCategory(category, pageable);
    }
    public PostResponseDTO.PostDetailDTO getPostDetail(Long postPk) {

        Post post = postRepository.findById(postPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.POST_NOT_FOUND));

        List<String> hashtags = post.getHashtags().stream()
                .map(Hashtag::getName)
                .toList();

        List<HotNews> news = post.getRecommendations()
                .stream()
                .map(r -> HotNews.builder()
                        .newsPk(r.getRecommendationPk())
                        .title(r.getTitle())
                        .url(r.getUrl())
                        .build()
                )
                .toList();

        return PostConverter.toPostDetailDTO(post, hashtags, news);
    }

}
