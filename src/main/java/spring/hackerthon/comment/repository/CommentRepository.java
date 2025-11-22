package spring.hackerthon.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.comment.domain.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentPk(long commentPk);
}
