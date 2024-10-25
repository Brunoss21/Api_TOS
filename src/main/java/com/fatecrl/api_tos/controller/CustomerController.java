package com.fatecrl.api_tos.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.api_tos.model.Customer;
import com.fatecrl.api_tos.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
        
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable ("id") Long id) {
        Customer customer = customerService.getCustomerByid(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/name")
    public List<Customer> getCustomersByName(@RequestParam(required = false) String name){
        if (name != null){
            return customerService.findCustumerByName(name);
        } else {
            return customerService.getAllCustomers();
        }
    }


    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        return ResponseEntity.created(location).body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        customer.setId(id);
        if (customerService.updateCustomer(customer)) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        boolean hasPendingOperations = customerService.checkPendingOperations(id);
        
        if (hasPendingOperations) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O cliente possui cargas pendentes ou em trânsito e não pode ser excluído.");
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Customer> updateCustomerStatus(@PathVariable("id") Long id, @RequestParam String status) {
        Customer updatedCustomer = customerService.updateStatus(id, status);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }
}

    


