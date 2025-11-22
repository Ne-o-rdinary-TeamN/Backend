package spring.hackerthon.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.news.domain.News;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop5ByOrderByNewsPkDesc();
}
