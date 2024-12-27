package org.example.backend.repositories;

import org.example.backend.models.PhieuGiamGiaChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhieuGiamGiaChiTietRepository extends JpaRepository<PhieuGiamGiaChiTiet, UUID> {
}