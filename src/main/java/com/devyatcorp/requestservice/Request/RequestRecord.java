package com.devyatcorp.requestservice.Request;


import javax.persistence.*;

@Entity
@Table(name="request")
public class RequestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusEnum status = StatusEnum.NEW;

    @Column(name="description")
    private String description;

    @Override
    public String toString() {
        return "RequestRecord{" +
                "id=" + id +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public RequestRecord() {
    }



    public RequestRecord(String description) {
        this.description = description;
    }

    public RequestRecord(StatusEnum status) {
        this.status = status;
    }

    public RequestRecord(StatusEnum status, String description) {
        this.status = status;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
