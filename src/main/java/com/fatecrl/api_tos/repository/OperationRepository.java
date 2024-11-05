package com.fatecrl.api_tos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecrl.api_tos.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByStatus(String status);

    List<Operation> findByOperationStartOrOperationEnd(LocalDateTime operationStart, LocalDateTime operationEnd);

    List<Operation> findAll();
}