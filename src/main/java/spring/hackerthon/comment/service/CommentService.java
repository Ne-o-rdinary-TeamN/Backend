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
import spring.hackerthon.comment.dto.Comments;
import spring.hackerthon.comment.dto.CommentsListRes;
import spring.hackerthon.comment.repository.CommentLikeRepository;
import spring.hackerthon.comment.repository.CommentRepository;
import spring.hackerthon.global.error.exception.handler.GeneralHandler;
import spring.hackerthon.global.response.ApiResponse;
import spring.hackerthon.global.response.status.ErrorStatus;
import spring.hackerthon.opinion.domain.Opinion;
import spring.hackerthon.opinion.repository.OpinionRepository;
import spring.hackerthon.global.security.JwtPrincipal;
import spring.hackerthon.opinion.domain.OpinionType;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.post.repository.PostRepository;
import spring.hackerthon.user.domain.User;
import spring.hackerthon.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final OpinionRepository opinionRepository;
    private final EntityManager em;

    @Transactional
    public Comment createComment(Long userPk, Long postPk, CommentRequestDTO.CommentCreateDTO request) {
        //존재하는 유저인지 검증
        User user = userRepository.findById(userPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        //존재하는 투표글인지 검증
        Post post = postRepository.findById(postPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.POST_NOT_FOUND));

        Opinion opinion = opinionRepository.findByUserAndPost_PostPk(user, postPk)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.OPINION_NOT_FOUND));

        //투표를 한 유저만 댓글을 작성할 수 있음
        if (!opinion.getPost().getPostPk().equals(post.getPostPk())) {
            throw new GeneralHandler(ErrorStatus.OPINION_DO_NOT_MATCH);
        }

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

    @Transactional
    public CommentsListRes getComments(long postPk, String option) {
        List<Comments> cList = new ArrayList<>();

        List<Comment> list = null;
        if("ALL".equals(option)) {
            list = commentRepository.findAllByPost_PostPk(postPk);
        } else {
            list = commentRepository.findAllCommentsByFiltering(OpinionType.valueOf(option), postPk);
        }

        for(Comment c : list) {
            Comments newC = Comments.builder().commentPk(c.getCommentPk()).userId(c.getUser().getUserId()).content(c.getContent()).likes(c.getLikeCount()).build();
            cList.add(newC);
        }

        return CommentsListRes.builder().list(cList).build();
    }
}
