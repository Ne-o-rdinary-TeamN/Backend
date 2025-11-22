package spring.hackerthon.opinion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.opinion.domain.Opinion;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Long countByUser_UserPk(long userPk);
}
