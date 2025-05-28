package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.dto.request.CashDisbursementOrderDto;
import io.tsuderia.km124_api.api.service.CashDisbursementOrderService;
import io.tsuderia.km124_api.data.entity.CashDisbursementOrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cash_disbursement_orders")
@AllArgsConstructor
public class CashDisbursementOrderController {

    private final CashDisbursementOrderService cashDisbursementOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<CashDisbursementOrderEntity> getCashDisbursementOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(cashDisbursementOrderService.findCashDisbursementOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<CashDisbursementOrderEntity>> getAllCashDisbursementOrders() {
        return ResponseEntity.ok(cashDisbursementOrderService.findAllCashDisbursementOrders());
    }

    @PostMapping
    public ResponseEntity<CashDisbursementOrderEntity> cashDisbursementOrder (@RequestBody CashDisbursementOrderDto cashDisbursementOrder) {
        return ResponseEntity.ok(cashDisbursementOrderService.createCashDisbursementOrder(cashDisbursementOrder));
    }
}
