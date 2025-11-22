package spring.hackerthon.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.hackerthon.post.domain.Category;
import spring.hackerthon.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Long countByUser_UserPk(long userPk);
    Page<Post> findAllByCategory(Category category, Pageable pageable);
}
