package org.example.backend.services;

import org.example.backend.models.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class DiaChiService extends GenericServiceImpl<DiaChi, UUID>{
    public DiaChiService(JpaRepository<DiaChi, UUID> repository) {
        super(repository);
    }
}
