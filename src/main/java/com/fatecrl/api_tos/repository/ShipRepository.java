package com.fatecrl.api_tos.repository;

import com.fatecrl.api_tos.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    
}
