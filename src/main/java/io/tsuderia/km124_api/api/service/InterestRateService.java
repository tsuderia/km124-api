package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.data.entity.InterestRateEntity;
import io.tsuderia.km124_api.data.repository.InterestRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InterestRateService {
    private final InterestRateRepository interestRateRepository;

    public InterestRateEntity findInterestRateById(Long id) {
        return interestRateRepository.findById(id).orElseThrow(() -> new RuntimeException("Interest rate not found"));
    }

    public List<InterestRateEntity> findAllInterestRates() {
        return interestRateRepository.findAll();
    }

    public InterestRateEntity createInterestRate(InterestRateEntity interestRate) {
        return interestRateRepository.save(InterestRateEntity.builder()
                .interestRate(interestRate.getInterestRate())
                .build());
    }

    public InterestRateEntity updateInterestRate(InterestRateEntity interestRate, Long id) {
        InterestRateEntity interestRateToUpdate = findInterestRateById(id);
        interestRateToUpdate.setInterestRate(interestRate.getInterestRate());
        return interestRateRepository.save(interestRateToUpdate);
    }

    public void deleteInterestRateById(Long id) {
        interestRateRepository.deleteById(id);
    }
}
