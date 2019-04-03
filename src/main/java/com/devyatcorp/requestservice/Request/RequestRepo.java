package com.devyatcorp.requestservice.Request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<RequestRecord, Long> {
    RequestRecord getById(long id);

    boolean existsById(long id);
}
