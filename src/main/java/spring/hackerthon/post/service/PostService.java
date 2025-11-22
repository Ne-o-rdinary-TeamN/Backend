package spring.hackerthon.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.opinion.domain.Opinion;
import spring.hackerthon.opinion.repository.OpinionRepository;
import spring.hackerthon.post.converter.PostConverter;
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
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final OpinionRepository opinionRepository;

    @Transactional
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

    public List<PostResponseDTO.SinglePostViewResultDTO> getParticipatePost(Long userPk) {

        User user = userRepository.findById(userPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        List<Opinion> opinionList = opinionRepository.findAllByUser(user);

        return opinionList.stream()
                .map(op -> PostConverter.toSinglePostViewResultDTO(op.getPost()))
                .toList();
    }

    public List<PostResponseDTO.SinglePostViewResultDTO> getMyPost(Long userPk) {

        User user = userRepository.findById(userPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        List<Post> postList = postRepository.findAllByUser(user);

        return postList.stream()
                .map(PostConverter::toSinglePostViewResultDTO)
                .toList();
    }
}
