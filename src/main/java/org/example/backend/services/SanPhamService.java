package org.example.backend.services;

import org.example.backend.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class SanPhamService extends GenericServiceImpl<SanPham , UUID> {
    public SanPhamService(JpaRepository<SanPham, UUID> repository) {
        super(repository);
    }
}
