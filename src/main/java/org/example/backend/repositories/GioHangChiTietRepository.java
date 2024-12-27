package org.example.backend.repositories;

import org.example.backend.models.GioHang;
import org.example.backend.models.GioHangChiTiet;
import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {

    List<GioHangChiTiet> findByIdGioHangAndDeletedFalse(GioHang gioHang);
    Optional<GioHangChiTiet> findByIdGioHangAndIdSpctAndDeletedFalse(GioHang gioHang, SanPhamChiTiet sanPhamChiTiet);
}