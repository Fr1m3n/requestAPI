package com.devyatcorp.requestservice.Request.DTO;


import com.devyatcorp.requestservice.Request.RequestRecord;
import com.devyatcorp.requestservice.Request.StatusEnum;

import java.util.Objects;

public class RequestDto {

    private Long id;
    private String description;
    private StatusEnum status;

    public RequestDto(RequestRecord record){
        this.id = record.getId();
        this.description = record.getDescription();
        this.status = record.getStatus();
    }

    public RequestDto(Long id, String description, StatusEnum status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }

    public RequestDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
