package org.example.backend.repositories;

import org.example.backend.dto.response.banHang.gioHangResponseAdmin;
import org.example.backend.models.GioHang;
import org.example.backend.models.GioHangChiTiet;
import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {

    List<GioHangChiTiet> findByIdGioHangAndDeletedFalse(GioHang gioHang);
    Optional<GioHangChiTiet> findByIdGioHangAndIdSpctAndDeletedFalse(GioHang gioHang, SanPhamChiTiet sanPhamChiTiet);


    //gio hang admin
    @Query(value =  "FROM gio_hang_chi_tiet ghct " +
            "JOIN gio_hang gh ON gh.id = ghct.id_gio_hang " +
            "JOIN san_pham_chi_tiet spct ON spct.id = ghct.id_spct " +
            "JOIN nguoi_dung nd ON nd.id = gh.id_nguoi_dung " +
            "JOIN hoa_don hd ON hd.id_nguoi_dung = nd.id",
            nativeQuery = true)
    List<gioHangResponseAdmin> getGioHangChiTiets();
}