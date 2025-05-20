package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.api.dto.request.EmployeeRequestDto;
import io.tsuderia.km124_api.api.dto.response.EmployeeResponseDto;
import io.tsuderia.km124_api.api.dto.mapper.EmployeeMapper;
import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import io.tsuderia.km124_api.data.entity.RoleEntity;
import io.tsuderia.km124_api.data.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
        EmployeeEntity employee = employeeMapper.toEntity(dto);
        RoleEntity role = roleService.findRoleById(dto.getRoleId());
        employee.setRole(role);
        employee.setCreatedAt(LocalDate.now());
        EmployeeEntity employeeToSave = employeeRepository.save(employee);
        return employeeMapper.toDto(employeeToSave);
    }

    public EmployeeResponseDto findEmployeeById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found")));
    }

    public List<EmployeeResponseDto> findAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    public EmployeeEntity findEmployeeEntityById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
