package com.fatecrl.api_tos.model;

import java.time.LocalDateTime;
import java.util.List;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
public class Operation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operationType;
    private String description;
    private LocalDateTime operationStart;
    private LocalDateTime operationEnd;
    private String status;
    private String shipOperation;

    
    @ManyToMany
    @JoinTable(
        name = "operation_containers",
        joinColumns = @JoinColumn(name = "operation_id"),
        inverseJoinColumns = @JoinColumn(name = "container_id")
    )

    private List<Container> containers;
    
}

