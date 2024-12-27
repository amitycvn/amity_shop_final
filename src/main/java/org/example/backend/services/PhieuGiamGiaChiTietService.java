package org.example.backend.services;

import org.example.backend.models.PhieuGiamGiaChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class PhieuGiamGiaChiTietService extends GenericServiceImpl<PhieuGiamGiaChiTiet , UUID> {
    public PhieuGiamGiaChiTietService(JpaRepository<PhieuGiamGiaChiTiet, UUID> repository) {
        super(repository);
    }
}
