package com.fatecrl.api_tos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatecrl.api_tos.model.Ship;
import com.fatecrl.api_tos.service.ShipService;

public class ShipController {

   /*  @Autowired
    private ShipService shipService;

    @GetMapping
    public List<Ship> getAllShips(){
        return shipService.findAllShips();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ship> getShipById(@PathVariable Long id){
        return shipService.findShipByid(id)
                .map(ship -> ResponseEntity.ok().body(ship))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nameship/{nameShip}")
    public List<Ship> getShipsByName(@PathVariable String nameShip){
        return shipService.findShipByName(nameShip);

    }

    @PostMapping
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship){
        Ship createdShip = shipService.saveShip(ship);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShip);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable Long id, @RequestBody Ship ship){
        
    }


  */  
}
