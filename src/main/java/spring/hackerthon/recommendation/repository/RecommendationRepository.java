package spring.hackerthon.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.hackerthon.recommendation.domain.Recommendation;
import spring.hackerthon.post.domain.Post;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
