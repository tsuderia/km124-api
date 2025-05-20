package io.tsuderia.km124_api.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeRequestDto {

    @NotBlank(message = "Employee name is required!")
    private String fullName;

    @NotBlank(message = "Phone number is required!")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank
    @NotNull
    @Min(4)
    private String password;
    @NotNull
    private Long roleId;
}
