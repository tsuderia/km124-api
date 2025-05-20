package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
