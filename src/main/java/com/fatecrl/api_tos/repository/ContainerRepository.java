package com.fatecrl.api_tos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatecrl.api_tos.model.Container;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
    List<Container> findByContainerNumber(String containerNumber);
}
