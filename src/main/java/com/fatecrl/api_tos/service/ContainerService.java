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

    public List<Container> findAll(){
        return containerRepository.findAll();
    }

    public Container findById(Long id) {
        return containerRepository.findById(id).orElse(null);
    }

    public boolean containerInactive(long id) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()){
            Container container = optionalContainer.get();
            container.setStatus("Inativo");
            containerRepository.save(container);
            return true;
        }
        return false;

    }

    public Container updateContainer(Long id, Container containerDetails) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            container.setContainerNumber(containerDetails.getContainerNumber());
            container.setType(containerDetails.getType());
            container.setLocation(containerDetails.getLocation());
            container.setWeight(containerDetails.getWeight());
            container.setArrivalDate(containerDetails.getArrivalDate());
            return containerRepository.save(container);
        }
        return null; 
    }

    public Container updateContainerStatus(Long id, String status) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()) {
            Container container = optionalContainer.get();
            container.setStatus(status); 
            return containerRepository.save(container); 
        }
        return null; 
    }

    public String deleteContainer(Long id) {
        Optional<Container> optionalContainer = containerRepository.findById(id);
        if (optionalContainer.isPresent()){
            Container container = optionalContainer.get();
            container.setStatus("Inativo");
            containerRepository.save(container);
            return "O container foi desativado com sucesso.";
        }
        return "Container não encontrado";
    }
}

    

