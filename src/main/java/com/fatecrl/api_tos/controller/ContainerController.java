package com.fatecrl.api_tos.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.api_tos.model.Container;
import com.fatecrl.api_tos.service.ContainerService;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @GetMapping
    public List<Container> getAllContainers(){
        return containerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> findContainerById(@PathVariable Long id) {
        Container container = containerService.findById(id);
        return container != null ? ResponseEntity.ok(container) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Container> createContainer(@RequestBody Container container) {
        Container savedContainer = containerService.save(container); 
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedContainer.getId()) 
                .toUri();
        return ResponseEntity.created(location).body(savedContainer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable Long id) {
        boolean deleted = containerService.containerInactive(id); 
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Container> updateContainer(@PathVariable Long id, @RequestBody Container containerDetails) {
        Container updatedContainer = containerService.updateContainer(id, containerDetails); 
        return updatedContainer != null ? ResponseEntity.ok(updatedContainer) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Container> updateContainerStatus(@PathVariable Long id, @RequestBody String status) {
        Container updatedContainer = containerService.updateContainerStatus(id, status); 
        if (updatedContainer != null ){
            return ResponseEntity.ok(updatedContainer); 
        }
           return ResponseEntity.notFound().build();
    }
}
    
    

