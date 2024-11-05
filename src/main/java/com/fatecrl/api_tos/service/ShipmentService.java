/*package com.fatecrl.api_tos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatecrl.api_tos.model.Shipment;
import com.fatecrl.api_tos.repository.ShipmentRepository;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;


    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public boolean deleteShipment(Long id) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        if (shipment.isPresent() && !shipment.get().getStatus().equals("Em Andamento")) {
            shipmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Shipment> updateShipment(Long id, Shipment shipmentDetails) {
        return shipmentRepository.findById(id).map(shipment -> {
            shipment.setShipmentNumber(shipmentDetails.getShipmentNumber());
            shipment.setDescription(shipmentDetails.getDescription());
            shipment.setShipmentDate(shipmentDetails.getShipmentDate());
            shipment.setTotalWeight(shipmentDetails.getTotalWeight());
            shipment.setCustomer(shipmentDetails.getCustomer());
            shipment.setContainers(shipmentDetails.getContainers());
            return shipmentRepository.save(shipment);
        });
    }

    public Optional<Shipment> updateShipmentStatus(Long id, String status) {
        return shipmentRepository.findById(id).map(shipment -> {
            shipment.setStatus(status);
            return shipmentRepository.save(shipment);
        });
    }
}*/
