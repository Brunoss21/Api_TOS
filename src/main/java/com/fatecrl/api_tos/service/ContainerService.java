package com.fatecrl.api_tos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecrl.api_tos.repository.ContainerRepository;

@Service
public class ContainerService {

    @Autowired
    private ContainerRepository containerRepository;

    
}
