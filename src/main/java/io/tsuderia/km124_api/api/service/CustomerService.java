package io.tsuderia.km124_api.api.service;

import io.tsuderia.km124_api.data.entity.CustomerEntity;
import io.tsuderia.km124_api.data.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public List<CustomerEntity> findAllCustomers() {
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }

    public CustomerEntity updateCustomer(CustomerEntity customer, Long id) {
        CustomerEntity customerToUpdate = findCustomerById(id);

        customerToUpdate.setFullName(customer.getFullName());
        customerToUpdate.setPassport(customer.getPassport());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setDescription(customer.getDescription());
        customerToUpdate.setUpdatedAt(LocalDate.now());

        return customerRepository.save(customerToUpdate);
    }
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
