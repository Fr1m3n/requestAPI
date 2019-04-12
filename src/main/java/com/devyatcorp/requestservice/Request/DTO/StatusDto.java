package com.devyatcorp.requestservice.Request.DTO;

import com.devyatcorp.requestservice.Request.StatusEnum;

public class StatusDto {
    private StatusEnum status;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusDto(StatusEnum status) {
        this.status = status;
    }
}
