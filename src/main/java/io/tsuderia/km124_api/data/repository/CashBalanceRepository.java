package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.CashBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CashBalanceRepository extends JpaRepository<CashBalanceEntity, Long> {
    Optional<CashBalanceEntity> findTopByOrderByIdDesc();
}
