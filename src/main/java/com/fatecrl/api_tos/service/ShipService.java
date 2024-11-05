package com.fatecrl.api_tos.service;

import com.fatecrl.api_tos.model.Ship;
import com.fatecrl.api_tos.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public Optional<Ship> getShipById(Long id) {
        return shipRepository.findById(id);
    }

    public Ship createShip(Ship ship) {
        return shipRepository.save(ship);
    }

    /* 
    public void inactivateShip(Long id) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Navio não encontrado com id: " + id));

        // Verificação de embarques programados (adapte conforme seu modelo)
        if (ship.getOperations().isEmpty()) {
            ship.setStatus("Inativo");  // Atualiza o status para inativo
            shipRepository.save(ship);
        } else {
            throw new IllegalStateException("Navio possui embarques programados e não pode ser inativado.");
        }
    }

    public Ship updateShip(Long id, Ship updatedShip) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Navio não encontrado com id: " + id));

        ship.setNameShip(updatedShip.getNameShip());
        ship.setRegistrationNumber(updatedShip.getRegistrationNumber());
        ship.setShipFlag(updatedShip.getShipFlag());
        ship.setStatus(updatedShip.getStatus());
        ship.setCapacity(updatedShip.getCapacity());
        ship.setArrivalDate(updatedShip.getArrivalDate());
        ship.setDepartureDate(updatedShip.getDepartureDate());

        return shipRepository.save(ship);
    }
        */
}

