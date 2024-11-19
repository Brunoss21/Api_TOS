package com.fatecrl.api_tos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameShip;
    private String registrationNumber;
    private String shipFlag;
    private String status;
    private Double capacity;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    
}
