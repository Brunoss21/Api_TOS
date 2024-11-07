package com.fatecrl.api_tos.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationDTO {
    private String operationType;
    private String description;
    private LocalDateTime operationStart;
    private LocalDateTime operationEnd;
    private String status;
    private String shipOperation;
    private List<Long> containerIds = new ArrayList<>();

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
    public List<Long> getContainerIds() {
        return containerIds;
    }
    public void setContainerIds(List<Long> containerIds) {
        this.containerIds = containerIds;
    }

}
