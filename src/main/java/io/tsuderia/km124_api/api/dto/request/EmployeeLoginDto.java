package io.tsuderia.km124_api.api.dto.request;

import lombok.Data;

@Data
public class EmployeeLoginDto {
    private String phoneNumber;
    private String password;
}
