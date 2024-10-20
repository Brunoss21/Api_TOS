package com.fatecrl.api_tos.model;

import java.time.LocalDateTime;


public class Operation {
    
    private Long id;
    private String operationType;
    private String description;
    private LocalDateTime operationStart;
    private LocalDateTime operationEnd;
    private String status;
    private String shipOperation;
    private List<Container> containers;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOperationType() {
        return operationType;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getOperationStart() {
        return operationStart;
    }
    public void setOperationStart(LocalDateTime operationStart) {
        this.operationStart = operationStart;
    }
    public LocalDateTime getOperationEnd() {
        return operationEnd;
    }
    public void setOperationEnd(LocalDateTime operationEnd) {
        this.operationEnd = operationEnd;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getShipOperation() {
        return shipOperation;
    }
    public void setShipOperation(String shipOperation) {
        this.shipOperation = shipOperation;
    }
    public List<Container> getContainers() {
        return containers;
    }
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Operation other = (Operation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
