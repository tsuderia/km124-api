package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.service.DocumentTypeService;
import io.tsuderia.km124_api.data.entity.DocumentTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/document_types")
@AllArgsConstructor
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @PostMapping
    public ResponseEntity<DocumentTypeEntity> createDocumentType(DocumentTypeEntity documentType) {
        return ResponseEntity.ok(documentTypeService.createDocumentType(documentType));
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeEntity>> getAllDocumentTypes() {
        return ResponseEntity.ok(documentTypeService.getAllDocumentTypes());
    }
}
