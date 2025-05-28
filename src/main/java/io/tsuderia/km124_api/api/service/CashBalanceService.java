package io.tsuderia.km124_api.api.service;


import io.tsuderia.km124_api.data.entity.*;
import io.tsuderia.km124_api.data.repository.CashBalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CashBalanceService {

    private final CashBalanceRepository cashbalanceRepository;

    private final DocumentService documentService;
    private final BuyOutService buyOutService;
    private final CashReceiptOrderService cashReceiptOrderService;
    private final CashDisbursementOrderService cashDisbursementOrderService;

    public CashBalanceEntity initCashBalance() {

        List<DocumentEntity> allDocuments = documentService.findAllDocuments();
        List<BuyOutEntity> allBuyOuts = buyOutService.findAllBuyOuts();
        List<CashReceiptOrderEntity> allCashReceiptOrders = cashReceiptOrderService.findAllCashReceiptOrders();
        List<CashDisbursementOrderEntity> allCashDisbursementOrders = cashDisbursementOrderService.findAllCashDisbursementOrders();
        float totalAmountOfDocuments = -allDocuments.stream().map(DocumentEntity::getAmount).reduce(0f, Float::sum);
        float totalAmountOfBuyOuts = allBuyOuts.stream().map(BuyOutEntity::getAmount).reduce(0f, Float::sum);
        float totalAmountOfCashReceiptOrders = allCashReceiptOrders.stream().map(CashReceiptOrderEntity::getAmount).reduce(0f, Float::sum);
        float totalAmountOfCashDisbursementOrders = -allCashDisbursementOrders.stream().map(CashDisbursementOrderEntity::getAmount).reduce(0f, Float::sum);

        float cashBalanceValue = totalAmountOfDocuments + totalAmountOfBuyOuts + totalAmountOfCashReceiptOrders + totalAmountOfCashDisbursementOrders;

        CashBalanceEntity cashBalance = new CashBalanceEntity();
        cashBalance.setCash(cashBalanceValue);
        return cashbalanceRepository.save(cashBalance);
    }

    public CashBalanceEntity getCashBalance() {
        return cashbalanceRepository.findTopByOrderByIdDesc()
                .orElseGet(() -> {
                    // Если нет баланса, инициализируем его
                    return initCashBalance();
                });
    }
    public CashBalanceEntity addToCashBalance(float amount) {
       return null;
    }
}
