package com.fatecrl.api_tos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fatecrl.api_tos.model.Operation;
import com.fatecrl.api_tos.service.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getById(@PathVariable Long id) {
        Optional<Operation> operation = operationService.findById(id);
        return operation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Operation> create(@RequestBody Operation operation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(operationService.save(operation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operation> update(@PathVariable Long id, @RequestBody Operation operation) {
        if (operationService.findById(id).isPresent()) {
            operation.setId(id); // Garantir que estamos atualizando
            return ResponseEntity.ok(operationService.save(operation));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (operationService.findById(id).isPresent()) {
            operationService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

