package io.tsuderia.km124_api.api.dto.mapper;

import io.tsuderia.km124_api.api.dto.request.EmployeeRequestDto;
import io.tsuderia.km124_api.api.dto.response.EmployeeResponseDto;
import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeEntity toEntity(EmployeeRequestDto dto) {
        return EmployeeEntity.builder()
                .fullName(dto.getFullName())
                .passwordHash(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public EmployeeResponseDto toDto(EmployeeEntity entity) {
        return EmployeeResponseDto.builder()
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .roleName(entity.getRole().getName())
                .build();
    }

}
