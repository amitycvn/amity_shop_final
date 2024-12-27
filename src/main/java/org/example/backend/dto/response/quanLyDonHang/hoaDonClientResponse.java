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
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class hoaDonClientResponse {

    private UUID id;

    private String maHD;

    private String tenKhachHang;

    private String soDienThoai;

    private String diaChi;

    private BigDecimal giaGoc;

    private BigDecimal giaGiam;

    private BigDecimal tongTien;

    private String ghiChu;

    private Instant ngayDuKienNhan;

    private BigDecimal tienVanChuyen;

    private String loaiHoaDon;

    private Instant ngayTao;

    private String trangThai;

    private Boolean deleted;

    // Thông tin chi tiết hóa đơn
    private List<hoaDonChiTietReponse> hoaDonChiTiets;
}
