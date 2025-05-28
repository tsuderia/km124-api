package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByFullNameContainingIgnoreCase(String query);
}
