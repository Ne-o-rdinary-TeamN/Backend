package spring.hackerthon.post.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;

import spring.hackerthon.opinion.repository.OpinionRepository;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.dto.PostResponseDTO;

import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.opinion.domain.Opinion;
import spring.hackerthon.opinion.domain.OpinionType;
import spring.hackerthon.opinion.repository.OpinionRepository;
import spring.hackerthon.post.domain.Hashtag;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.dto.VoteReq;

import spring.hackerthon.news.dto.HotNews;
import spring.hackerthon.post.converter.PostConverter;
import spring.hackerthon.post.domain.Category;

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
    private final EntityManager em;

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

    @Transactional
    public Boolean vote(JwtPrincipal user, VoteReq req) {
        User userRef = em.getReference(User.class, user.userPk());

        Post p = postRepository.findByPostPk(req.postPk()).orElseThrow(() -> new GeneralHandler(ErrorStatus.POST_NOT_FOUND));

        OpinionType type = OpinionType.valueOf(req.opinion());

        //tb_opinion 로우 추가
        Opinion opinion = Opinion.builder().opinion(type).user(userRef).post(p).build();
        opinionRepository.save(opinion);

        //게시글 찬성/반대 표 수정, 찬성/반대 비율 수정, 전체 표 수정
        p.updatePost(type);
        postRepository.save(p);

        return Boolean.TRUE;
    }
  
    public Page<Post> getPostList(Category category, Pageable pageable) {

        if (category == null) {
            return postRepository.findAll(pageable);
        }
        return postRepository.findAllByCategory(category, pageable);
    }


    public PostResponseDTO.PostDetailDTO getPostDetail(Long postPk, JwtPrincipal user) {
        boolean participated = opinionRepository.existsByUser_UserPkAndPost_PostPk(user.userPk(), postPk);
        System.out.println("participated: " + participated);
        System.out.println("userPk:" + user.userPk());
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

        return PostConverter.toPostDetailDTO(participated, post, hashtags, news);
    }
}
