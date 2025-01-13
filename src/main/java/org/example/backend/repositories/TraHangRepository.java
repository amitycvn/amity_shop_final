package org.example.backend.repositories;

import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
import org.example.backend.models.HoaDon;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.models.TraHang;
import org.example.backend.dto.response.traHang.TraHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TraHangRepository extends JpaRepository<TraHang, UUID> {

    TraHang findTraHangByIdNguoiDungAndIdHoaDonAndIdSanPhamChiTiet(NguoiDung nguoiDung, HoaDon hoaDon, SanPhamChiTiet sanPhamChiTiet);

    List<TraHang> findAllByIdHoaDon(HoaDon hoaDon);
}
