package org.example.backend.repositories;

import org.example.backend.models.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, UUID> {
}