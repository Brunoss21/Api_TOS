package com.fatecrl.api_tos.controller;

import com.fatecrl.api_tos.model.Ship;
import com.fatecrl.api_tos.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

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
}
