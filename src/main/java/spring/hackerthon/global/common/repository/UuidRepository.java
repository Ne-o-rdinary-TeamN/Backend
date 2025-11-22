package spring.hackerthon.global.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.hackerthon.global.common.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
