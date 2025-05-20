package io.tsuderia.km124_api.api.controller;

import io.tsuderia.km124_api.api.service.CustomerService;
import io.tsuderia.km124_api.data.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }
    @PostMapping
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> updateCustomerById(@RequestBody CustomerEntity customer, @PathVariable Long id) {
        return ResponseEntity.ok(customerService.updateCustomer(customer, id));
    }
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
