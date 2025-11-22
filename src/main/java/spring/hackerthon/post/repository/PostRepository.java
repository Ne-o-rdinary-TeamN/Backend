package spring.hackerthon.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.hackerthon.post.domain.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Long countByUser_UserPk(long userPk);
    Optional<Post> findByPostPk(long postPk);
}
