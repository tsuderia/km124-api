package io.tsuderia.km124_api.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CashDisbursementOrderDto {
    private Long id;
    private Long employeeId;
    private float amount;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
