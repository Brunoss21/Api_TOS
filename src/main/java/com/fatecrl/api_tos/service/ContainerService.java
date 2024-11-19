package com.fatecrl.api_tos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fatecrl.api_tos.model.Container;
import com.fatecrl.api_tos.repository.ContainerRepository;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    public Page<Container> findAll(Pageable pageable) {
        return containerRepository.findAll(pageable);
    }

    public Optional<Container> findById(Long id) {
        return containerRepository.findById(id);
    }

    public Container save(Container container) {
        return containerRepository.save(container);
    }

    public String deleteContainer(Long id) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            container.setStatus("Inativo");
            containerRepository.save(container);
            return "O container foi desativado com sucesso.";
        }
        return "Container não encontrado";
    }
}



    

