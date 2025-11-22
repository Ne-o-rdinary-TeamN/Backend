package spring.hackerthon.comment.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import spring.hackerthon.comment.domain.Comment;
import spring.hackerthon.comment.domain.CommentLike;
import spring.hackerthon.comment.dto.CommentRequestDTO;
import spring.hackerthon.comment.repository.CommentLikeRepository;
import spring.hackerthon.comment.repository.CommentRepository;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.repository.PostRepository;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final EntityManager em;

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

    @Transactional
    public Boolean likeComment(JwtPrincipal user, Long commentPk) {
        //이미 해당 댓글에 좋아요를 누른 경우 오류
        if(commentLikeRepository.existsByUser_UserPkAndComment_CommentPk(user.userPk(), commentPk)) {
            throw new GeneralHandler(ErrorStatus.BAD_REQUEST);
        }

        Comment c = commentRepository.findByCommentPk(commentPk).orElseThrow(() -> new GeneralHandler(ErrorStatus.COMMENT_NOT_FOUND));
        User userRef = em.getReference(User.class, user.userPk());

        //tb_comment_like 로우 추가
        CommentLike cm = CommentLike.builder().comment(c).user(userRef).build();
        commentLikeRepository.save(cm);

        //댓글 좋아요 개수 업데이트
        c.updateLikes();
        commentRepository.save(c);

        return Boolean.TRUE;
    }
}
