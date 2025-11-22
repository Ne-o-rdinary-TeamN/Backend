package spring.hackerthon.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hackerthon.comment.domain.Comment;
import spring.hackerthon.comment.dto.CommentRequestDTO;
import spring.hackerthon.comment.repository.CommentRepository;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.repository.PostRepository;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment createComment(Long userPk, Long postPk, CommentRequestDTO.CommentCreateDTO request) {
        //존재하는 유저인지 검증
        User user = userRepository.findById(userPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        //존재하는 투표글인지 검증
        Post post = postRepository.findById(postPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.POST_NOT_FOUND));

        //댓글 생성
        Comment createdComment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .post(post)
                .build();

        return commentRepository.save(createdComment);
    }
}
