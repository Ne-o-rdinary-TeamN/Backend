package spring.hackerthon.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.post.domain.Post;
import spring.hackerthon.user.domain.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser (User user);
}
