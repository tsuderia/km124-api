package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.data.entity.DocumentTypeEntity;
import io.tsuderia.km124_api.data.repository.DocumentTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeEntity findDocumentTypeById(Long id) {
        return documentTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Document type not found"));
    }

    public DocumentTypeEntity createDocumentType(DocumentTypeEntity documentType) {
        return documentTypeRepository.save(documentType);
    }
}
