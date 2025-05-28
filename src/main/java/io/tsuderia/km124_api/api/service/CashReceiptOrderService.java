package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.api.dto.request.CashReceiptOrderDto;
import io.tsuderia.km124_api.data.entity.CashReceiptOrderEntity;
import io.tsuderia.km124_api.data.entity.EmployeeEntity;
import io.tsuderia.km124_api.data.repository.CashReceiptOrderRepository;
import io.tsuderia.km124_api.data.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CashReceiptOrderService {
    private final CashReceiptOrderRepository cashReceiptOrderRepository;
    private final EmployeeService employeeService;

    public CashReceiptOrderEntity createCashReceiptOrder(CashReceiptOrderDto dto) {
        if (dto.getEmployeeId() != null) {
            EmployeeEntity employee = employeeService.findEmployeeEntityById(dto.getEmployeeId());
            return cashReceiptOrderRepository.save(CashReceiptOrderEntity.builder()
                    .amount(dto.getAmount())
                    .description(dto.getDescription())
                    .employee(employee)
                    .createdAt(LocalDate.now())
                    .build());
        } else {
            return cashReceiptOrderRepository.save(CashReceiptOrderEntity.builder()
                    .amount(dto.getAmount())
                    .description(dto.getDescription())
                    .createdAt(LocalDate.now())
                    .build());
        }
    }

    public CashReceiptOrderEntity findCashReceiptOrderById(Long id) {
        return cashReceiptOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cash receipt order not found"));
    }

    public List<CashReceiptOrderEntity> findAllCashReceiptOrders() {
        return cashReceiptOrderRepository.findAll();
    }

    public CashReceiptOrderEntity updateCashReceiptOrderById(CashReceiptOrderEntity cashReceiptOrder, Long id) {
        CashReceiptOrderEntity cashReceiptOrderToUpdate = findCashReceiptOrderById(id);
        EmployeeEntity employee = employeeService.findEmployeeEntityById(cashReceiptOrder.getEmployee().getId());

        if (employee != null) {
            cashReceiptOrderToUpdate.setEmployee(employee);
        }

        cashReceiptOrderToUpdate.setAmount(cashReceiptOrder.getAmount());
        cashReceiptOrderToUpdate.setDescription(cashReceiptOrder.getDescription());

        return cashReceiptOrderRepository.save(cashReceiptOrderToUpdate);
    }
}
