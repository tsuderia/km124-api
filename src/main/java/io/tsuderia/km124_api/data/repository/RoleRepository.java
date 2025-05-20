package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
