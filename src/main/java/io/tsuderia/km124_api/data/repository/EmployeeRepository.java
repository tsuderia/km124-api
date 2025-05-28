package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);
    Optional<EmployeeEntity> findByPhoneNumberAndPasswordHash(String phoneNumber, String passwordHash);
}
