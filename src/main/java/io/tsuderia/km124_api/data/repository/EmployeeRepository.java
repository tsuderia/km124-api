package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
