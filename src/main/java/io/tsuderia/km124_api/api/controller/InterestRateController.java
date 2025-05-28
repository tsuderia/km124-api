package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.service.InterestRateService;
import io.tsuderia.km124_api.data.entity.InterestRateEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interest_rate")
@AllArgsConstructor
public class InterestRateController {
    private final InterestRateService interestRateService;

    @GetMapping
    public ResponseEntity<List<InterestRateEntity>> getAllInterestRates() {
        return ResponseEntity.ok(interestRateService.findAllInterestRates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestRateEntity> getInterestRateById(@PathVariable Long id) {
        return ResponseEntity.ok(interestRateService.findInterestRateById(id));
    }

    @PostMapping
    public ResponseEntity<InterestRateEntity> createInterestRate(@RequestBody InterestRateEntity interestRate) {
        return ResponseEntity.ok(interestRateService.createInterestRate(interestRate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterestRateEntity> updateInterestRateById(@RequestBody InterestRateEntity interestRate, @PathVariable Long id){
        return ResponseEntity.ok(interestRateService.updateInterestRate(interestRate, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInterestRateById(@PathVariable Long id) {
        interestRateService.deleteInterestRateById(id);
        return ResponseEntity.ok("Deleted interest rate with id: " + id);
    }
}
