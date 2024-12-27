package org.example.backend.repositories;

import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.GioHang;
import org.example.backend.models.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GioHangRepository extends JpaRepository<GioHang, UUID> {
    @Query("""
        SELECT GioHang from GioHang g where g.deleted=false and g.idNguoiDung.id =:id
        """)
    GioHang finbyIDKH(UUID id);

    Optional<GioHang> findByIdNguoiDungAndDeletedFalse(NguoiDung nguoiDung);
    
}