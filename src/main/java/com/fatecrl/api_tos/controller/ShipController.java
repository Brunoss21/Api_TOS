package com.fatecrl.api_tos.controller;

import com.fatecrl.api_tos.model.Ship;
import com.fatecrl.api_tos.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

     // Buscar navios pelo nome
     @GetMapping("/search/name")
     public ResponseEntity<Page<Ship>> findShipsByName(
             @RequestParam String name,
             Pageable pageable) {
         Page<Ship> ships = shipService.findShipsByName(name, pageable);
         return ResponseEntity.ok(ships);
     }
 
     // Buscar navios pelo status
     @GetMapping("/search/status")
     public ResponseEntity<Page<Ship>> findShipsByStatus(
             @RequestParam String status,
             Pageable pageable) {
         Page<Ship> ships = shipService.findShipsByStatus(status, pageable);
         return ResponseEntity.ok(ships);
     }
 
     // Buscar navios com capacidade maior que um valor
     @GetMapping("/search/capacity")
     public ResponseEntity<Page<Ship>> findShipsByCapacityGreaterThan(
             @RequestParam Integer capacity,
             Pageable pageable) {
         Page<Ship> ships = shipService.findShipsByCapacityGreaterThan(capacity, pageable);
         return ResponseEntity.ok(ships);
     }
 
     // Buscar navios entre duas datas de chegada
     @GetMapping("/search/arrival-dates")
     public ResponseEntity<Page<Ship>> findShipsByArrivalDateRange(
             @RequestParam LocalDateTime startDate,
             @RequestParam LocalDateTime endDate,
             Pageable pageable) {
         Page<Ship> ships = shipService.findShipsByArrivalDateRange(startDate, endDate, pageable);
         return ResponseEntity.ok(ships);
     }

    // Buscar um navio pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable Long id) {
        Optional<Ship> ship = shipService.getShipById(id);
        return ship.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cria um novo navio
    @PostMapping
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        Ship newShip = shipService.createShip(ship);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShip);
    }

    // Atualizar um navio
    @PutMapping("/{id}")
    public ResponseEntity<String> updateShip(@PathVariable Long id, @RequestBody Ship updatedShip) {
        String ship = shipService.updateShip(id, updatedShip);
        if (ship != null) {
            return ResponseEntity.ok(ship);
        }
        return ResponseEntity.notFound().build();
    }

    // Inativa um navio
    @DeleteMapping("/{id}")
    public ResponseEntity<String> inactivateShip(@PathVariable Long id) {
        try {
            shipService.inactivateShip(id);
            return ResponseEntity.ok("Navio desativado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao desativar o navio: " + e.getMessage());
        }
    }

    // Busca todos os navios com paginação
    @GetMapping
    public ResponseEntity<Page<Ship>> getAllShips(
            @RequestParam(required = false) String nameShip, 
            Pageable pageable) {
        if (nameShip != null && !nameShip.isEmpty()) {
            return ResponseEntity.ok(shipService.findByNameShip(nameShip, pageable));
        } else {
            return ResponseEntity.ok(shipService.findAll(pageable));
        }
    }
    
}
