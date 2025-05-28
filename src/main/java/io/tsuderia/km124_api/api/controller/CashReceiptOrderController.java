package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.dto.request.CashReceiptOrderDto;
import io.tsuderia.km124_api.api.service.CashReceiptOrderService;
import io.tsuderia.km124_api.data.entity.CashReceiptOrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cash_receipt_orders")
@AllArgsConstructor
public class CashReceiptOrderController {

    private final CashReceiptOrderService cashReceiptOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<CashReceiptOrderEntity> getCashReceiptOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(cashReceiptOrderService.findCashReceiptOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<CashReceiptOrderEntity>> getAllCashReceiptOrders() {
        return ResponseEntity.ok(cashReceiptOrderService.findAllCashReceiptOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CashReceiptOrderEntity> updateCashReceiptOrder(@RequestBody CashReceiptOrderEntity cashReceiptOrder,
                                                                         @PathVariable Long id) {
        return ResponseEntity.ok(cashReceiptOrderService.updateCashReceiptOrderById(cashReceiptOrder, id));
    }

    @PostMapping
    public ResponseEntity<CashReceiptOrderEntity> createCashReceiptOrder(@RequestBody CashReceiptOrderDto cashReceiptOrder) {
        return ResponseEntity.ok(cashReceiptOrderService.createCashReceiptOrder(cashReceiptOrder));
    }
}
