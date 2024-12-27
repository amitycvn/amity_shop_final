package org.example.backend.services;

import org.example.backend.models.Hang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class HangService extends GenericServiceImpl<Hang , UUID> {
    public HangService(JpaRepository<Hang, UUID> repository) {
        super(repository);
    }
}
