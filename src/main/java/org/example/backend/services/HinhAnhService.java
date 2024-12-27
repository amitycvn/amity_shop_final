package org.example.backend.services;

import org.example.backend.models.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class HinhAnhService extends GenericServiceImpl<HinhAnh, UUID> {
    public HinhAnhService(JpaRepository<HinhAnh, UUID> repository) {
        super(repository);
    }
}
