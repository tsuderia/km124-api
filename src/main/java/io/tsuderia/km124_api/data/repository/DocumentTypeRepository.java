package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.DocumentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Long> {
}
