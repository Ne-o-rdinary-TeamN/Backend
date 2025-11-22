package spring.hackerthon.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
