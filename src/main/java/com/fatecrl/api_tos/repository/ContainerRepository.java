package com.fatecrl.api_tos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fatecrl.api_tos.model.Container;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {

    // Busca por número do container
    List<Container> findByContainerNumber(String containerNumber);

    // Busca por ID, status ou cliente
    @Query("SELECT c FROM Container c WHERE (:id IS NULL OR c.id = :id) AND (:status IS NULL OR c.status = :status) AND (:customerId IS NULL OR c.customer.id = :customerId)")
    List<Container> findContainersByCriteria(
        @Param("id") Long id, 
        @Param("status") String status, 
        @Param("customerId") Long customerId
    );
}
