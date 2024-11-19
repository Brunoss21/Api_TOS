package com.fatecrl.api_tos.repository;

import com.fatecrl.api_tos.model.Ship;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    // Query para buscar navios pelo nome
    Page<Ship> findByNameShipContainingIgnoreCase(String name, Pageable pageable);

    // Query para buscar navios pelo status
    Page<Ship> findByStatus(String status, Pageable pageable);

    // Query para buscar navios com capacidade maior que um valor
    Page<Ship> findByCapacityGreaterThan(Integer capacity, Pageable pageable);

    // Query para buscar navios entre duas datas de chegada
    Page<Ship> findByArrivalDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    // Busca navio por ID
    Optional<Ship> findById(Long id);

}
