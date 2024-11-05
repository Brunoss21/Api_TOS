package com.fatecrl.api_tos.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.api_tos.model.Customer;
import com.fatecrl.api_tos.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        return customer != null ? ResponseEntity.ok(customer): ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCustomer);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustumer(@PathVariable Long id, @RequestBody Customer customerDetails){
        Customer updatedCustomer = customerService.updateCustomer(id,customerDetails);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateCustomerStatus(@PathVariable Long id, @RequestBody String status){
        String updatedCustomer = customerService.updateCustomerStatus(id, status);
        if (updatedCustomer != null){
            return ResponseEntity.ok(updatedCustomer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String result = customerService.deleteCustumer(id);
        if (result.equals("Cliente desativado com sucesso")){
            return ResponseEntity.ok().body("{\"message\": \"" + result + "\"}");
        }
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("{\"error\": \"Cliente não encontrado.\"}");
    }
}


    


