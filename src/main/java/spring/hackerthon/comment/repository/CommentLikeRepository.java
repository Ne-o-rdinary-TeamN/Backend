package spring.hackerthon.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.comment.domain.CommentLike;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}
