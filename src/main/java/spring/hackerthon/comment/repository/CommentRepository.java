package spring.hackerthon.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
