package com.fatecrl.api_tos.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.fatecrl.api_tos.model.Customer;

public class CustomerService {

    public void deleteCustomer(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

    public Customer updateStatus(Long id, String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStatus'");
    }

    public boolean updateCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    public void saveCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCustomer'");
    }

    public java.util.List<Customer> findCustumerByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCustumerByName'");
    }

    public java.util.List<Customer> getAllCustomers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }

    public Customer getCustomerByid(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerByid'");
    }

    public boolean checkPendingOperations(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkPendingOperations'");
    }

    @Autowired
    private CustomerRepository CustomerRepository;
    
}
