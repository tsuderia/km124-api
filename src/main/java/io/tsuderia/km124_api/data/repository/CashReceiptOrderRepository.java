package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.CashReceiptOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashReceiptOrderRepository extends JpaRepository<CashReceiptOrderEntity, Long> {
}
