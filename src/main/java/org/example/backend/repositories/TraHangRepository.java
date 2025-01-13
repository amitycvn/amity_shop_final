package org.example.backend.repositories;


import org.example.backend.models.HoaDon;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.models.TraHang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TraHangRepository extends JpaRepository<TraHang, UUID> {

    TraHang findTraHangByIdNguoiDungAndIdHoaDonAndIdSanPhamChiTiet(NguoiDung nguoiDung, HoaDon hoaDon, SanPhamChiTiet sanPhamChiTiet);


    @Query("SELECT COUNT(*) = SUM(CASE WHEN t.trangThai = :trangThai THEN 1 ELSE 0 END) " +
            "FROM TraHang t WHERE t.idHoaDon.id = :idHoaDon and t.idHoaDon.trangThai = :statusHd")
    boolean areAllOrdersCompleted(@Param("idHoaDon") UUID idHoaDon, @Param("trangThai") String trangThai,@Param("statusHd") String statusHd);

    @Query("SELECT h.trangThai FROM HoaDon h WHERE h.id = :idHoaDon and h.trangThai=:tt")
    String findTrangThaiByIdHoaDon(@Param("idHoaDon") UUID idHoaDon ,String tt);


    List<TraHang> findAllByIdHoaDon(HoaDon hoaDon);

