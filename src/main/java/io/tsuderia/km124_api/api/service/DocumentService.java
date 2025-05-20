package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.api.dto.mapper.EmployeeMapper;
import io.tsuderia.km124_api.api.dto.request.DocumentRequestDto;
import io.tsuderia.km124_api.api.dto.response.EmployeeResponseDto;
import io.tsuderia.km124_api.data.entity.*;
import io.tsuderia.km124_api.data.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    private final EmployeeService employeeService;

    private final CustomerService customerService;

    private final GoldPurityService goldPurityService;

    private final DocumentTypeService documentTypeService;

//    public DocumentEntity createDocument(DocumentEntity document) {
//
//        GoldPurityEntity goldPurityEntity = goldPurityService.findGoldPurityById(document.getGoldPurity().getId());
//        float pricePerGram = goldPurityEntity.getPricePerGram();
//        CustomerEntity customer = customerService.findCustomerById(document.getCustomer().getId());
//        EmployeeResponseDto employee = employeeService.findEmployeeById(document.getEmployee().getId());
//        DocumentTypeEntity documentType = documentTypeService.findDocumentTypeById(document.getDocumentType().getId());
//
//        document.setDocumentType(documentType);
//        document.setCustomer(customer);
//        document.setGoldPurity(goldPurityEntity);
//
//        document.setDateOfTermination(LocalDate.now().plusDays(60));
//        float evaluation = (document.getWeight() - document.getWeightOfGems()) * pricePerGram;
//        document.setEvaluation(evaluation);
//        document.setAmount(evaluation);
//
//        return documentRepository.save(document);
//    }

    public DocumentEntity createDocumentFromDto(DocumentRequestDto dto) {
        GoldPurityEntity goldPurity = goldPurityService.findGoldPurityById(dto.getGoldPurityId());
        CustomerEntity customer = customerService.findCustomerById(dto.getCustomerId());
        EmployeeEntity employee = employeeService.findEmployeeEntityById(dto.getEmployeeId()); // ⚠️ не DTO
        DocumentTypeEntity documentType = documentTypeService.findDocumentTypeById(dto.getDocumentTypeId());

        float pricePerGram = goldPurity.getPricePerGram();
        float evaluation = (dto.getWeight() - dto.getWeightOfGems()) * pricePerGram;

        DocumentEntity document = DocumentEntity.builder()
                .goldPurity(goldPurity)
                .customer(customer)
                .employee(employee)
                .documentType(documentType)
                .items(dto.getItems())
                .weight(dto.getWeight())
                .weightOfGems(dto.getWeightOfGems())
                .evaluation(evaluation)
                .amount(evaluation)
                .build();

        return documentRepository.save(document);
    }


    public DocumentEntity findDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found!"));
    }

    public List<DocumentEntity> findAllDocuments() {
        return documentRepository.findAll().stream().collect(Collectors.toList());
    }
}
