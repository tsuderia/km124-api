package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.service.GoldPurityService;
import io.tsuderia.km124_api.data.entity.GoldPurityEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gold_purity")
@AllArgsConstructor
public class GoldPurityController {
    private final GoldPurityService goldPurityService;

    @PostMapping
    public ResponseEntity<GoldPurityEntity> createGoldPurity(@RequestBody GoldPurityEntity goldPurity) {
        return ResponseEntity.ok(goldPurityService.createGoldPurity(goldPurity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoldPurityEntity> updateGoldPurity(@RequestBody GoldPurityEntity goldPurity, @PathVariable Long id) {
        return ResponseEntity.ok(goldPurityService.updateGoldPurity(goldPurity, id));
    }

    @GetMapping
    public ResponseEntity<List<GoldPurityEntity>> getAllGoldPurities() {
        return ResponseEntity.ok(goldPurityService.findAllGoldPurities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoldPurityEntity> getGoldPurityById(@PathVariable Long id) {
        return ResponseEntity.ok(goldPurityService.findGoldPurityById(id));
    }

//    TODO: delete
}
