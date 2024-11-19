package com.fatecrl.api_tos.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatecrl.api_tos.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByStatus(String status);

    List<Operation> findByOperationStartOrOperationEnd(LocalDateTime operationStart, LocalDateTime operationEnd);

    List<Operation> findAll();

    Page<Operation> findByStatus(String status, Pageable pageable);

    // Consultar todas as operações de um determinado tipo
    @Query("SELECT o FROM Operation o WHERE o.operationType = ?1")
    List<Operation> findByOperationType(String operationType);

    /*// Consulta para buscar uma operação pelo nome do navio
    @Query("SELECT o FROM Operation o JOIN o.ship s WHERE s.nameShip LIKE %?1%")
    Page<Operation> findByShipName(String nameShip, Pageable pageable);

    // Consulta para buscar uma operação entre duas datas
    @Query("SELECT o FROM Operation o WHERE o.arrivalDate BETWEEN ?1 AND ?2")
    List<Operation> findByArrivalDateBetween(String startDate, String endDate);*/
}