package com.fatecrl.api_tos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatecrl.api_tos.model.Customer;
import com.fatecrl.api_tos.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public String updateCustomerStatus(Long id, String status) {
        Optional<Customer> optionalCustumer = customerRepository.findById(id);
        if(optionalCustumer.isPresent()) {
            Customer customer = optionalCustumer.get();
            customer.setStatus(status);
            customerRepository.save(customer);
            return "Status atualizado com sucesso.";
        }
        return "Erro ao atualizar status do cliente";
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}



