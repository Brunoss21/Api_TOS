package com.fatecrl.api_tos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecrl.api_tos.model.Container;
import com.fatecrl.api_tos.repository.ContainerRepository;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    public Container save(Container container) {
        return containerRepository.save(container);
    }

    public List<Container> findAll() {
        return containerRepository.findAll();
    }

    public Container findById(Long id) {
        return containerRepository.findById(id).orElse(null);
    }

    public String updateContainer(Long id, Container containerDetails) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            container.setContainerNumber(containerDetails.getContainerNumber());
            container.setType(containerDetails.getType());
            container.setLocation(containerDetails.getLocation());
            container.setWeight(containerDetails.getWeight());
            container.setArrivalDate(containerDetails.getArrivalDate());
            containerRepository.save(container);
            return "Container atualizado com sucesso.";
        }
        return "Erro ao atualizar container";
    }

    public String updateContainerStatus(Long id, String status) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            container.setStatus(status);
            containerRepository.save(container);
            return "Status Container atualizado com sucesso.";
        }
        return "Erro ao atualizar status do container";
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

    // Novo método para buscar contêineres por ID, Status ou Cliente
    public List<Container> findContainersByCriteria(Long id, String status, Long customerId) {
        return containerRepository.findContainersByCriteria(id, status, customerId);
    }
}


    

