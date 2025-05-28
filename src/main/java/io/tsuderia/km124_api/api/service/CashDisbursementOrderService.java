package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.api.dto.request.CashDisbursementOrderDto;
import io.tsuderia.km124_api.data.entity.CashDisbursementOrderEntity;
import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import io.tsuderia.km124_api.data.repository.CashDisbursementOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CashDisbursementOrderService {

    private final CashDisbursementOrderRepository cashDisbursementOrderRepository;
    private final EmployeeService employeeService;

    public List<CashDisbursementOrderEntity> findAllCashDisbursementOrders() {
        return cashDisbursementOrderRepository.findAll();
    }

    public CashDisbursementOrderEntity findCashDisbursementOrderById(Long id) {
        return cashDisbursementOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cash disbursement order not found"));
    }

    public CashDisbursementOrderEntity createCashDisbursementOrder(CashDisbursementOrderDto dto) {
        CashDisbursementOrderEntity cashDisbursementOrderToCreate = new CashDisbursementOrderEntity();
        Long employeeId = dto.getEmployeeId();
        EmployeeEntity employee = employeeService.findEmployeeEntityById(employeeId);
        employee = employeeService.findEmployeeEntityById(employeeId);
        cashDisbursementOrderToCreate.setEmployee(employee);


        cashDisbursementOrderToCreate.setCreatedAt(LocalDate.now());
        cashDisbursementOrderToCreate.setDescription(dto.getDescription());
        cashDisbursementOrderToCreate.setAmount(dto.getAmount());
        return cashDisbursementOrderRepository.save(cashDisbursementOrderToCreate);
    }
}
