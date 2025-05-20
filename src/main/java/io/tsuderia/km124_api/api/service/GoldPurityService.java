package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.data.entity.GoldPurityEntity;
import io.tsuderia.km124_api.data.repository.GoldPurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GoldPurityService {

    private final GoldPurityRepository goldPurityRepository;

    public GoldPurityEntity findGoldPurityById(Long id) {
        return goldPurityRepository.findById(id).orElseThrow(() -> new RuntimeException("Probe not found"));
    }

    public float findPricePerGramById(Long id) {
        GoldPurityEntity goldPurity = findGoldPurityById(id);
        return goldPurity.getPricePerGram();
    }

    public GoldPurityEntity createGoldPurity(GoldPurityEntity goldPurity) {
        return goldPurityRepository.save(goldPurity);
    }

    public List<GoldPurityEntity> findAllGoldPurities() {
        return goldPurityRepository.findAll().stream().collect(Collectors.toList());
    }

    public GoldPurityEntity updateGoldPurity(GoldPurityEntity goldPurity, Long id){

        GoldPurityEntity newGoldPurity = findGoldPurityById(id);

        newGoldPurity.setName(goldPurity.getName());
        newGoldPurity.setPricePerGram(goldPurity.getPricePerGram());
        newGoldPurity.setUpdatedAt(LocalDate.now());

        return goldPurityRepository.save(newGoldPurity);
    }

    public void deleteGoldPurityById(Long id) {goldPurityRepository.deleteById(id);}
}
