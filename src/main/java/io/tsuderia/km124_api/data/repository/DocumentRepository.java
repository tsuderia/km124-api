package io.tsuderia.km124_api.data.repository;

import io.tsuderia.km124_api.data.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByIdOrCustomerFullNameContainingIgnoreCase(@Param("id") Long id, @Param("name") String name);
    List<DocumentEntity> findByCustomerFullNameContainingIgnoreCase(String fullName);
}
