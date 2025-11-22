package spring.hackerthon.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.hackerthon.comment.domain.Comment;
import spring.hackerthon.opinion.domain.OpinionType;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentPk(long commentPk);

    List<Comment> findAllByPost_PostPk(long postPk);

    @Query("""
    SELECT c
    FROM Comment c LEFT JOIN Opinion o ON c.user = o.user
    WHERE o.opinion = :opinion AND c.post.postPk = :postPk
    """)
    List<Comment> findAllCommentsByFiltering(@Param("opinion") OpinionType opinion, @Param("postPk") long postPk);
}
