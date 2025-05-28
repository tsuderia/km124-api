package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.api.dto.mapper.EmployeeMapper;
import io.tsuderia.km124_api.api.dto.request.DocumentRequestDto;
import io.tsuderia.km124_api.api.dto.request.DocumentWithBuyoutDto;
import io.tsuderia.km124_api.api.dto.request.EmployeeRequestDto;
import io.tsuderia.km124_api.api.dto.response.EmployeeResponseDto;
import io.tsuderia.km124_api.data.entity.*;
import io.tsuderia.km124_api.data.repository.BuyOutRepository;
import io.tsuderia.km124_api.data.repository.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final GoldPurityService goldPurityService;
    private final DocumentTypeService documentTypeService;
    private final InterestRateService interestRateService;
    private final BuyOutRepository buyOutRepository;


    public DocumentEntity createDocumentFromDto(DocumentRequestDto dto) {

        CustomerEntity customer = customerService.findCustomerById(dto.getCustomerId());
        EmployeeEntity employee = employeeService.findEmployeeEntityById(dto.getEmployeeId());
        DocumentTypeEntity documentType = documentTypeService.findDocumentTypeById(dto.getDocumentTypeId());
        InterestRateEntity interestRate = interestRateService.findInterestRateById(dto.getInterestRateId());
        GoldPurityEntity goldPurity = goldPurityService.findGoldPurityById(dto.getGoldPurityId());

        if (goldPurity.getId() != 3) {
            float pricePerGram;
            float evaluation = dto.getEvaluation();

            if (evaluation != 0 && dto.getAmount() != 0){
                if (goldPurity != null) {
                    pricePerGram = goldPurity.getPricePerGram();
                    evaluation = (dto.getWeight() - dto.getWeightOfGems()) * pricePerGram;
                } else {
                    evaluation = dto.getEvaluation();
                }
            }

            float commissionFee = dto.getAmount() * interestRate.getInterestRate() * 30;

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
                    .interestRate(interestRate)
                    .commissionFee(commissionFee)
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .isBuyOut(dto.isBuyOut())
                    .dateOfTermination(LocalDate.now().plusDays(60))
                    .build();

            return documentRepository.save(document);
        } else {
            DocumentEntity document = DocumentEntity.builder()
                    .goldPurity(goldPurity)
                    .customer(customer)
                    .employee(employee)
                    .documentType(documentType)
                    .items(dto.getItems())
                    .weight(dto.getWeight())
                    .weightOfGems(dto.getWeightOfGems())
                    .evaluation(dto.getEvaluation())
                    .amount(dto.getAmount())
                    .interestRate(interestRate)
                    .commissionFee(dto.getCommissionFee())
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .isBuyOut(dto.isBuyOut())
                    .dateOfTermination(LocalDate.now().plusDays(60))
                    .build();

            return documentRepository.save(document);
        }
    }

    public List<DocumentWithBuyoutDto> getAllDocumentsWithBuyouts() {
        List<DocumentEntity> documents = documentRepository.findAll();
        List<BuyOutEntity> buyouts = buyOutRepository.findAll();

        Map<Long, BuyOutEntity> buyoutMap = buyouts.stream().collect(Collectors.toMap(b -> b.getDocumentReference().getId(), b -> b));

        return documents.stream().map(doc -> {
            BuyOutEntity buyout = buyoutMap.get(doc.getId());

            return new DocumentWithBuyoutDto(
                    doc.getId(),
                    doc.getCustomer().getFullName(),
                    doc.getEmployee().getFullName(),
                    doc.getDocumentType().getName(),
                    doc.getGoldPurity().getName(),
                    doc.getWeight(),
                    doc.getWeightOfGems(),
                    doc.getEvaluation(),
                    doc.getAmount(),
                    doc.getCommissionFee(),
                    doc.getDateOfTermination(),
                    doc.getCreatedAt(),
                    doc.getUpdatedAt(),
                    doc.getItems(),
                    buyout != null,
                    buyout != null ? buyout.getAmount() : null,
                    buyout != null ? buyout.getCreatedAt() : null
            );
        }).toList();
    }

    public List<DocumentEntity> searchDocuments(String query) {
        List<DocumentEntity> documents;

        if (query == null || query.isBlank()) {
            documents = documentRepository.findAll();
        } else {
            try {
                Long id = Long.parseLong(query);
                documents = documentRepository.findByIdOrCustomerFullNameContainingIgnoreCase(id, query);
            } catch (NumberFormatException e) {
                documents = documentRepository.findByIdOrCustomerFullNameContainingIgnoreCase(null,query);
            }
        }
        return documents.stream().collect(Collectors.toList());
    }

//    public List<DocumentEntity> searchDocuments(Long id, String clientName) {
//        if (id != null) {
//            return documentRepository.findById(id).stream().toList();
//        } else if (clientName != null && !clientName.isBlank()) {
//            return documentRepository.findByCustomerFullNameContainingIgnoreCase(clientName);
//        } else {
//            return documentRepository.findAll();
//        }
//    }




    public DocumentEntity findDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found!"));
    }

    public List<DocumentEntity> findAllDocuments() {
        return documentRepository.findAll().stream().collect(Collectors.toList());
    }

    public DocumentEntity updateDocumentById(DocumentRequestDto dto, Long id) {
        DocumentEntity documentToUpdate = findDocumentById(id);
        System.out.println(id);
        System.out.println(dto.toString());
        if (documentToUpdate == null) { return null; }

        CustomerEntity customer = customerService.findCustomerById(dto.getCustomerId());
        EmployeeEntity employee = employeeService.findEmployeeEntityById(dto.getEmployeeId());
        DocumentTypeEntity documentType = documentTypeService.findDocumentTypeById(dto.getDocumentTypeId());

        if (dto.getInterestRateId() != null) {
            InterestRateEntity interestRate = interestRateService.findInterestRateById(dto.getInterestRateId());
            documentToUpdate.setInterestRate(interestRate);
        }
        if (dto.getGoldPurityId() != null) {
            GoldPurityEntity goldPurity = goldPurityService.findGoldPurityById(dto.getGoldPurityId());
            documentToUpdate.setGoldPurity(goldPurity);
        }

        documentToUpdate.setDocumentType(documentType);
        documentToUpdate.setCustomer(customer);
        documentToUpdate.setEmployee(employee);


        documentToUpdate.setItems(dto.getItems());
        documentToUpdate.setWeight(dto.getWeight());
        documentToUpdate.setWeightOfGems(dto.getWeightOfGems());
        documentToUpdate.setEvaluation(dto.getEvaluation());
        documentToUpdate.setAmount(dto.getAmount());
        documentToUpdate.setUpdatedAt(LocalDate.now());

        return documentRepository.save(documentToUpdate);
    }
}
