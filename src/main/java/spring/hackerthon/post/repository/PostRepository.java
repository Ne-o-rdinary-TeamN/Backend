package spring.hackerthon.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.user.domain.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.user = :user")
    List<Post> findAllByUser (@Param("user") User user);
}
