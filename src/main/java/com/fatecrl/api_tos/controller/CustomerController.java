package com.fatecrl.api_tos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatecrl.api_tos.model.Customer;
import com.fatecrl.api_tos.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Mostra os todos os clientes com páginação
    @GetMapping
    public ResponseEntity<Page<Customer>> getAll(Pageable pageable) {
        try {
            Page<Customer> customers = customerService.findAll(pageable);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    // Obtem um cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
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

    // Atualizar as informações completas de um cliente
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        if (customerService.findById(id).isPresent()) {
            customer.setId(id);
            return ResponseEntity.ok(customerService.save(customer));
        }
        return ResponseEntity.notFound().build();
    }

    // Excluir um cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (customerService.findById(id).isPresent()) {
            customerService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}





    


