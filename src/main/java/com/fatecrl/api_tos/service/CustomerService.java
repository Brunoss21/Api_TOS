package com.fatecrl.api_tos.service;

import java.util.List;
import java.util.Optional;

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

    public String deleteCustumer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customer.setStatus("Inativo");
            customerRepository.save(customer);
            return "O cliente foi desativado com sucesso.";
        }
        return "Cliente não encontrado";
    }
}


