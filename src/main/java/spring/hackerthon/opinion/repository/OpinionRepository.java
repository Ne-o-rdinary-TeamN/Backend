package spring.hackerthon.opinion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.hackerthon.opinion.domain.Opinion;
import spring.hackerthon.user.domain.User;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Long countByUser_UserPk(long userPk);

    @Query("SELECT o FROM Opinion o JOIN FETCH o.post WHERE o.user = :user")
    List<Opinion> findAllByUser(@Param("user") User user);
}
