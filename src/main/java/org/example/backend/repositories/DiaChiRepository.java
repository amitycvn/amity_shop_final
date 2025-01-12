package org.example.backend.repositories;

import org.example.backend.models.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
    List<DiaChi> findByIdNguoiDung_Id(UUID idNguoiDung);
}