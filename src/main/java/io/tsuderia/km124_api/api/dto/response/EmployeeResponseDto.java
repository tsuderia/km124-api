package io.tsuderia.km124_api.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeResponseDto {
    private String fullName;
    private String phoneNumber;
    private String roleName;
}
