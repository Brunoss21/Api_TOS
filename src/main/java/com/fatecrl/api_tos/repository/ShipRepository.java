package com.fatecrl.api_tos.repository;

import com.fatecrl.api_tos.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    // Busca navios pelo nome 
    @Query("SELECT s FROM Ship s WHERE s.nameShip LIKE %:nameShip%")
    List<Ship> findByName(@Param("nameShip") String nameShip);

    /*// Busca navios pelo status ativo/inativo
    @Query("SELECT s FROM Ship s WHERE s.active = :active")
    List<Ship> findByActiveStatus(@Param("active") Boolean active);*/

    // Busca navios com capacidade maior que o valor fornecido
    @Query("SELECT s FROM Ship s WHERE s.capacity > :capacity")
    List<Ship> findByCapacityGreaterThan(@Param("capacity") Integer capacity);

    // Busca navio por ID
    Optional<Ship> findById(Long id);
}
