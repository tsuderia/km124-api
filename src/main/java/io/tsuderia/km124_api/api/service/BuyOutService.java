package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.api.dto.request.BuyOutRequestDto;
import io.tsuderia.km124_api.data.entity.BuyOutEntity;
import io.tsuderia.km124_api.data.entity.DocumentEntity;
import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import io.tsuderia.km124_api.data.repository.BuyOutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BuyOutService {

    private final DocumentService documentService;
    private final BuyOutRepository buyOutRepository;
    private final EmployeeService employeeService;

    public BuyOutEntity findBuyOutById(Long id) {
        return buyOutRepository.findById(id).orElseThrow(() -> new RuntimeException("Buy out not found"));
    }

    public BuyOutEntity createBuyOut(BuyOutRequestDto dto) {
        DocumentEntity documentToBuyOut = documentService.findDocumentById(dto.getDocumentReferenceId());

        if (documentToBuyOut.getIsBuyOut() == true) {
            throw new RuntimeException("Can't buy out document. Document have already been bought out");
        }

        LocalDate createdAt = documentToBuyOut.getCreatedAt();
        long days = ChronoUnit.DAYS.between(createdAt, LocalDate.now());

        float documentAmount = documentToBuyOut.getAmount();
        float commissionFee = documentAmount * (documentToBuyOut.getInterestRate().getInterestRate() / 100) * days;

        if (commissionFee < 50) {
            commissionFee = 50;
        }

        float amount = documentAmount + commissionFee;

        EmployeeEntity employee = employeeService.findEmployeeEntityById(dto.getEmployeeId());
        BuyOutEntity buyOut = new BuyOutEntity();
        buyOut.setDocumentReference(documentToBuyOut);
        buyOut.setEmployee(employee);
        buyOut.setAmount(amount);
        documentToBuyOut.setIsBuyOut(true);
        return buyOutRepository.save(buyOut);
    }

    public List<BuyOutEntity> findAllBuyOuts() {
        return buyOutRepository.findAll();
    }

    public BuyOutEntity updateBuyOut(BuyOutEntity buyOut, Long id) {
        BuyOutEntity buyOutToUpdate = findBuyOutById(id);
        EmployeeEntity employee = employeeService.findEmployeeEntityById(buyOut.getEmployee().getId());
        DocumentEntity document = documentService.findDocumentById(buyOut.getDocumentReference().getId());
        buyOutToUpdate.setUpdatedAt(LocalDate.now());
        buyOutToUpdate.setAmount(buyOut.getAmount());
        buyOutToUpdate.setEmployee(employee);
        buyOutToUpdate.setDocumentReference(document);

        return buyOutRepository.save(buyOutToUpdate);
    }

    public void deleteBuyOutById(Long id) {
        buyOutRepository.deleteById(id);
    }



    @Deprecated
    public BuyOutEntity createBuyOut(BuyOutEntity buyOut) {
        DocumentEntity documentToBuyOut = documentService.findDocumentById(buyOut.getDocumentReference().getId());

        long daysPassed = ChronoUnit.DAYS.between(documentToBuyOut.getCreatedAt(), LocalDate.now());

        float documentAmount = documentToBuyOut.getAmount();
        float commissionFee = documentAmount * (documentToBuyOut.getInterestRate().getInterestRate() / 100) * daysPassed;

        if (commissionFee < 50) {
            commissionFee = 50;
        }

        float amount = documentAmount + commissionFee;

        EmployeeEntity employee = employeeService.findEmployeeEntityById(buyOut.getEmployee().getId());

        buyOut.setDocumentReference(documentToBuyOut);
        buyOut.setEmployee(employee);
        buyOut.setAmount(amount);
        documentToBuyOut.setIsBuyOut(true);
        return buyOutRepository.save(buyOut);
    }

}
