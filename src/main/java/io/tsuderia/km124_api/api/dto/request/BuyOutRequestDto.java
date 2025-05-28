package io.tsuderia.km124_api.api.dto.request;

import lombok.Data;

@Data
public class BuyOutRequestDto {
    private Long documentReferenceId;
    private Long employeeId;
    private float interestRate;

}

