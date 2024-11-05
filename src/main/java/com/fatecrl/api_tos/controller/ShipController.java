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

    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable Long id) {
        Optional<Ship> ship = shipService.getShipById(id);
        return ship.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        Ship newShip = shipService.createShip(ship);
        return ResponseEntity.status(HttpStatus.CREATED).body(newShip);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> inactivateShip(@PathVariable Long id) {
        try {
            shipService.inactivateShip(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable Long id, @RequestBody Ship updatedShip) {
        try {
            Ship ship = shipService.updateShip(id, updatedShip);
            return ResponseEntity.ok(ship);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }*/
}
