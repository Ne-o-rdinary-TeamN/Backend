package spring.hackerthon.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.news.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
