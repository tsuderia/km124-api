package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.dto.request.DocumentRequestDto;
import io.tsuderia.km124_api.api.service.DocumentService;
import io.tsuderia.km124_api.data.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/documents")
@AllArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<DocumentEntity>> findAllDocuments() {
        return ResponseEntity.ok(documentService.findAllDocuments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentEntity> findDocumentById(Long id) {
        return ResponseEntity.ok(documentService.findDocumentById(id));
    }

//    @PostMapping
//    public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentEntity document) {
//        return ResponseEntity.ok(documentService.createDocument(document));
//    }

    @PostMapping
    public ResponseEntity<DocumentEntity> createDocument(@RequestBody DocumentRequestDto dto) {
        DocumentEntity created = documentService.createDocumentFromDto(dto);
        return ResponseEntity.ok(created);
    }


    // TODO: update, delete
}
