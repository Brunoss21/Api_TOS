package com.fatecrl.api_tos.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatecrl.api_tos.model.Operation;

import com.fatecrl.api_tos.repository.OperationRepository;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;


    public Page<Operation> findAll(Pageable pageable) {
        return operationRepository.findAll(pageable);
    }

    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public void delete(Long id) {
        operationRepository.deleteById(id);
    }
}

