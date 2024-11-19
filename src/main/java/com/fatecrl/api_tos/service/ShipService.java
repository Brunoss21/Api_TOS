package com.fatecrl.api_tos.service;

import com.fatecrl.api_tos.model.Ship;
import com.fatecrl.api_tos.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    // Busca navios pelo nome (com paginação)
    public Page<Ship> findShipsByName(String name, Pageable pageable) {
        return shipRepository.findByNameShipContainingIgnoreCase(name, pageable);
    }

    // Busca navios pelo status
    public Page<Ship> findShipsByStatus(String status, Pageable pageable) {
        return shipRepository.findByStatus(status, pageable);
    }

    // Busca navios com capacidade maior que um valor
    public Page<Ship> findShipsByCapacityGreaterThan(Integer capacity, Pageable pageable) {
        return shipRepository.findByCapacityGreaterThan(capacity, pageable);
    }

    // Busca navios entre duas datas de chegada
    public Page<Ship> findShipsByArrivalDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return shipRepository.findByArrivalDateBetween(startDate, endDate, pageable);
    
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
    public String updateShip(Long id, Ship updatedShip) {
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
            shipRepository.save(ship);
            return "Navio atualizado com sucesso.";
        }
        return "Erro ao atualizar navio"; 
    }
    

    public void inactivateShip(Long id) {
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Navio não encontrado com id: " + id));
        ship.setStatus("Inativo");
        shipRepository.save(ship);
    }

    // Busca todos os navios com paginação
    public Page<Ship> findAll(Pageable pageable) {
        return shipRepository.findAll(pageable);
    }

    // Busca navios por nome com paginação
    public Page<Ship> findByNameShip(String nameShip, Pageable pageable) {
        return shipRepository.findByNameShipContainingIgnoreCase(nameShip, pageable);
    }
}
