package spring.hackerthon.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.post.domain.Hashtag;
import spring.hackerthon.post.domain.Post;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    List<Hashtag> findAllByPost(Post post);
}
