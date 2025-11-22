package spring.hackerthon.recommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.recommendation.domain.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
}
