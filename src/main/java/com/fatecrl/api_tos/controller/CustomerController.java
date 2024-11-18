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

    // GET - Obter todos os clientes
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    // GET - Obter um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    // POST - Criar um novo cliente
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

    // PUT - Atualizar as informações completas de um cliente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        String updatedCustomer = customerService.updateCustomer(id, customerDetails);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    // PATCH - Atualizar o status do cliente (ativo/inativo)
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateCustomerStatus(@PathVariable Long id, @RequestBody String status) {
        String result = customerService.updateCustomerStatus(id, status);
        if (result.equals("Status atualizado com sucesso.")) {
            return ResponseEntity.ok("{\"message\": \"" + result + "\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Cliente não encontrado.\"}");
    }

    // DELETE - Excluir um cliente, desde que não possua cargas pendentes ou em trânsito
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String result = customerService.deleteCustumer(id);
        if (result.equals("Cliente excluído com sucesso.") || result.contains("desativado")) {
            return ResponseEntity.ok("{\"message\": \"" + result + "\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Cliente não encontrado.\"}");
    }
}


    


