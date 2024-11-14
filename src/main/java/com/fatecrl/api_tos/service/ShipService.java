package com.fatecrl.api_tos.service;

import com.fatecrl.api_tos.model.Ship;
import com.fatecrl.api_tos.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    // Busca navios por nome
    public List<Ship> getShipsByName(String name) {
        return shipRepository.findByName(name);
    }

    // Busca navios por status ativo
    /*public List<Ship> getShipsByActiveStatus(Boolean active) {
        return shipRepository.findByActiveStatus(active);
    }*/

    // Busca navios com capacidade maior que X
    public List<Ship> getShipsByCapacityGreaterThan(Integer capacity) {
        return shipRepository.findByCapacityGreaterThan(capacity);
    }

    // Busca navio por ID
    public Optional<Ship> getShipById(Long id) {
        return shipRepository.findById(id);
    }

    // Cria navio
    public Ship createShip(Ship ship) {
        return shipRepository.save(ship);
    }

    // Atualiza navio
    public Ship updateShip(Long id, Ship updatedShip) {
        Optional<Ship> existingShip = shipRepository.findById(id);
        if (existingShip.isPresent()) {
            Ship ship = existingShip.get();
            ship.setNameShip(updatedShip.getNameShip());
            ship.setRegistrationNumber(updatedShip.getRegistrationNumber());
            ship.setShipFlag(updatedShip.getShipFlag());
            ship.setStatus(updatedShip.getStatus());
            ship.setCapacity(updatedShip.getCapacity());
            ship.setArrivalDate(updatedShip.getArrivalDate());
            ship.setDepartureDate(updatedShip.getDepartureDate());
            return shipRepository.save(ship);
        }
        return null; 
    }
    

    public void inactivateShip(Long id) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Navio não encontrado com id: " + id));
        ship.setStatus("Inativo");
        shipRepository.save(ship);
    }
}

