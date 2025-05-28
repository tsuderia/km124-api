package io.tsuderia.km124_api.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class DocumentRequestDto {
    private Long id;
    private Long documentTypeId;
    private Long customerId;
    private Long employeeId;
    private Long goldPurityId;
    private Long interestRateId;
    private List<String> items;
    private float weight;
    private float evaluation;
    private float amount;
    private float commissionFee;
    private float weightOfGems;
    private LocalDate dateOfTermination;
    private boolean status;
    private boolean isBuyOut;
}
