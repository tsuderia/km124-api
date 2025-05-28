package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.service.CashBalanceService;
import io.tsuderia.km124_api.data.entity.CashBalanceEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cash_balance")
@AllArgsConstructor
public class CashBalanceController {

    private final CashBalanceService cashBalanceService;

    @GetMapping
    public ResponseEntity<CashBalanceEntity> getCashBalance() {
        return ResponseEntity.ok(cashBalanceService.initCashBalance());
    }
}
