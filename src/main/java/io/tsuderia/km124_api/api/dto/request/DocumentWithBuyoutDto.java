package io.tsuderia.km124_api.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentWithBuyoutDto {
    private Long documentId;
    private String customerFullName;
    private String employeeFullName;
    private String documentTypeName;
    private String goldPurityName;
    private float weight;
    private float weightOfGems;
    private float evaluation;
    private float amount;
    private float commissionFee;
    private LocalDate dateOfTermination;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<String> items;
    private boolean isBuyout;
    private Float buyoutAmount;
    private LocalDate buyoutDate;
}
