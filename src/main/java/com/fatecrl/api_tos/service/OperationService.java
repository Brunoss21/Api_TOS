package com.fatecrl.api_tos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecrl.api_tos.dto.OperationDTO;
import com.fatecrl.api_tos.model.Container;
import com.fatecrl.api_tos.model.Operation;
import com.fatecrl.api_tos.repository.ContainerRepository; // Importar ContainerRepository
import com.fatecrl.api_tos.repository.OperationRepository;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private ContainerRepository containerRepository; // Adicionar injeção do ContainerRepository

    // Buscar operações por data ou status
    public List<Operation> findByDateOrStatus(LocalDateTime date, String status) {
        if (date != null) {
            return operationRepository.findByOperationStartOrOperationEnd(date, date);
        } else if (status != null && !status.isEmpty()) {
            return operationRepository.findByStatus(status);
        } else {
            return operationRepository.findAll();
        }
    }

    public Operation createOperation(OperationDTO operationDTO) {
        Operation operation = new Operation();
        operation.setOperationType(operationDTO.getOperationType());
        operation.setDescription(operationDTO.getDescription());
        operation.setOperationStart(operationDTO.getOperationStart());
        operation.setOperationEnd(operationDTO.getOperationEnd());
        operation.setStatus(operationDTO.getStatus());
        operation.setShipOperation(operationDTO.getShipOperation());

        List<Container> containers = operationDTO.getContainerIds().stream()
            .map(id -> containerRepository.findById(id)
                .orElseThrow())
            .collect(Collectors.toList());

        operation.setContainers(containers);
        return operationRepository.save(operation);
    }

    // Atualizar status da operação
    public Optional<Operation> updateOperationStatus(Long id, String newStatus) {
        Optional<Operation> operation = operationRepository.findById(id);
        operation.ifPresent(op -> {
            op.setStatus(newStatus);
            operationRepository.save(op);
        });
        return operation;
    }
}
