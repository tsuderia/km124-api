package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.dto.request.EmployeeLoginDto;
import io.tsuderia.km124_api.api.dto.request.EmployeeRequestDto;
import io.tsuderia.km124_api.api.dto.response.EmployeeResponseDto;
import io.tsuderia.km124_api.api.service.EmployeeService;
import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeResponseDto> findEmployeeById(@PathVariable Long id) {
//        return ResponseEntity.ok(employeeService.findEmployeeEntityById(id));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> findEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeEntityById(id));
    }


    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> findAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @PostMapping("/login")
    public ResponseEntity<EmployeeEntity> login(@RequestBody EmployeeLoginDto request) {
        System.out.println("dsflsndfjlsnfjlsdnf");
        Optional<EmployeeEntity> employee = employeeService.findByPhoneNumber(request.getPhoneNumber());
        if (employee.isPresent() && employee.get().getPasswordHash().equals(request.getPassword())) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody EmployeeLoginDto request) {
//        EmployeeEntity employee = employeeService
//                .findByPhoneNumberAndPassword(request.getPhoneNumber(), request.getPassword());
//
//        if (employee == null) {
//            System.out.println(employee.toString());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные данные");
//        }
//
//        String token = jwtUtil.generateToken(employee);
//        System.out.println(Map.of(
//                "token", token,
//                "employee", employee));
//        return ResponseEntity.ok(Map.of(
//                "token", token,
//                "employee", employee
//        ));
    }




    //TODO: update, delete

