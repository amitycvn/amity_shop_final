package org.example.backend.services;

import org.example.backend.models.DeGiay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class DeGiayService extends GenericServiceImpl<DeGiay, UUID> {
    public DeGiayService(JpaRepository<DeGiay, UUID> repository) {
        super(repository);
    }
}
