package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.CashDisbursementOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashDisbursementOrderRepository extends JpaRepository<CashDisbursementOrderEntity, Long> {
}
