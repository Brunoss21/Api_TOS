package com.fatecrl.api_tos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatecrl.api_tos.model.Container;
import com.fatecrl.api_tos.service.ContainerService;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    // Lista os containers por página
    @GetMapping
    public ResponseEntity<Page<Container>> getAll(Pageable pageable) {
        try {
            Page<Container> containers = containerService.findAll(pageable);
            return ResponseEntity.ok(containers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Busca um container por ID
    @GetMapping("/{id}")
    public ResponseEntity<Container> getById(@PathVariable Long id) {
        Optional<Container> container = containerService.findById(id);
        return container.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cria um novo container
    @PostMapping
    public ResponseEntity<Container> create(@RequestBody Container container) {
        return ResponseEntity.status(HttpStatus.CREATED).body(containerService.save(container));
    }

    //Atualiza um container
    @PutMapping("/{id}")
    public ResponseEntity<Container> update(@PathVariable Long id, @RequestBody Container container) {
        if (containerService.findById(id).isPresent()) {
            container.setId(id);
            return ResponseEntity.ok(containerService.save(container));
        }
        return ResponseEntity.notFound().build();
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
}


    

