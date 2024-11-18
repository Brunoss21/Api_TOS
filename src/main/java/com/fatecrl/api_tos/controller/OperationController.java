package com.fatecrl.api_tos.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatecrl.api_tos.dto.OperationDTO;
import com.fatecrl.api_tos.model.Operation;
import com.fatecrl.api_tos.service.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    // Busca operações por data ou status
    @GetMapping
    public ResponseEntity<List<Operation>> getOperations(
            @RequestParam(required = false) LocalDateTime date,
            @RequestParam(required = false) String status) {
        List<Operation> operations = operationService.findByDateOrStatus(date, status);
        return ResponseEntity.ok(operations);
    }
 
    // Criar uma nova operação
    @PostMapping
    public ResponseEntity<Operation> createOperation(@RequestBody OperationDTO operationDTO) {
        Operation operation = operationService.createOperation(operationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(operation);
    }

    // Atualiza o status da operação
    @PatchMapping("/{id}/status")
    public ResponseEntity<Operation> updateOperationStatus(
            @PathVariable Long id, @RequestParam String status) {
        return operationService.updateOperationStatus(id, status)
                .map(updatedOperation -> ResponseEntity.ok(updatedOperation))
                .orElse(ResponseEntity.notFound().build());
    }
}
