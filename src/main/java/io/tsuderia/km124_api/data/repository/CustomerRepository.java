package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
