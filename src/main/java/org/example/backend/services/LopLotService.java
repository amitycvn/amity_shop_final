package org.example.backend.services;

import org.example.backend.models.LopLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class LopLotService extends GenericServiceImpl<LopLot, UUID> {
    public LopLotService(JpaRepository<LopLot, UUID> repository) {
        super(repository);
    }
}
