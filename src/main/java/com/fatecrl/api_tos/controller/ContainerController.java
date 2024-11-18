package com.fatecrl.api_tos.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatecrl.api_tos.model.Container;
import com.fatecrl.api_tos.service.ContainerService;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    // Lista todos os contêineres
    @GetMapping
    public List<Container> getAllContainers() {
        return containerService.findAll();
    }

    // Busca um container por ID
    @GetMapping("/{id}")
    public ResponseEntity<Container> findContainerById(@PathVariable Long id) {
        Container container = containerService.findById(id);
        return container != null ? ResponseEntity.ok(container) : ResponseEntity.notFound().build();
    }

    // Cria um novo container
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

    // Desativa um container
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContainer(@PathVariable Long id) {
        String result = containerService.deleteContainer(id);
        if (result.equals("O container foi desativado com sucesso.")) {
            return ResponseEntity.ok().body("{\"message\": \"" + result + "\"}");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"Container não encontrado.\"}");
    }

    //Atualiza um container
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContainer(@PathVariable Long id, @RequestBody Container containerDetails) {
        String updatedContainer = containerService.updateContainer(id, containerDetails);
        return updatedContainer != null ? ResponseEntity.ok(updatedContainer) : ResponseEntity.notFound().build();
    }

    // Atualiza o status de um contêiner
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateContainerStatus(@PathVariable Long id, @RequestBody String status) {
        String updatedContainer = containerService.updateContainerStatus(id, status);
        if (updatedContainer != null) {
            return ResponseEntity.ok(updatedContainer);
        }
        return ResponseEntity.notFound().build();
    }

    // Busca contêineres por ID, Status ou Cliente
    @GetMapping("/buscar")
    public ResponseEntity<List<Container>> searchContainers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long customerId) {

        List<Container> containers = containerService.findContainersByCriteria(id, status, customerId);
        if (containers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(containers);
    }
}

    

