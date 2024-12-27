package org.example.backend.dto.response.quanLyDonHang;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.models.HoaDon;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.models.SanPhamChiTiet;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class QuanLyDonHangRespose {

    private UUID id;

    private String maHD;

    private String tenKhachHang;

    private String soDienThoai;

    private String diaChi;

    private BigDecimal tongTien;

    private String loaiHoaDon;

    private Instant ngayTao;

    private String trangThai;

    private Boolean deleted;
}
