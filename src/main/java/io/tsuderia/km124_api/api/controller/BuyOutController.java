package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.dto.request.BuyOutRequestDto;
import io.tsuderia.km124_api.api.service.BuyOutService;
import io.tsuderia.km124_api.data.entity.BuyOutEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/buyouts")
@AllArgsConstructor
public class BuyOutController {
    private final BuyOutService buyOutService;

    @PostMapping
    public ResponseEntity<BuyOutEntity> createBuyOut(@RequestBody BuyOutRequestDto buyOutDto) {
        BuyOutEntity created = buyOutService.createBuyOut(buyOutDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyOutEntity> getBuyoutById(@PathVariable Long id) {
        return ResponseEntity.ok(buyOutService.findBuyOutById(id));
    }
}
