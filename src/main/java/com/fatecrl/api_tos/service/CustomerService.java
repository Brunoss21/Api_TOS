package com.fatecrl.api_tos.service;

import java.util.List;
import java.util.Optional; // Corrigido
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatecrl.api_tos.model.Customer;
import com.fatecrl.api_tos.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll(); 
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findByName(String name) {
        return (List<Customer>) customerRepository.findByName(name);
    }

    public Customer updateCustomer(Long id, Customer customerDetails){
        Customer customer = findById(id);
        if (customer != null){
            customer.setName((customerDetails.getName()));
            customer.setLastName(customerDetails.getLastName());
            customer.setAddress(customerDetails.getAddress());
            customer.setCity(customerDetails.getCity());
            customer.setState(customerDetails.getState());
            customer.setCountry(customerDetails.getCountry());
            customer.setBirthdate(customerDetails.getBirthdate());
            customer.setStatus(customerDetails.getStatus());
            return customerRepository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
