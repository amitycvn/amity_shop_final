package org.example.backend.dto.request.sanPham;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SanPhamChiTietRequest {
    private UUID id;
    private SanPham idSanPham;
    private MauSac idMauSac;
    private KichThuoc idKichThuoc;
    private String ten;
    private BigDecimal giaBan;
    private BigDecimal giaNhap;
    private String qrCode;
    private String moTa;
    private Integer soLuong;
    private Integer soLuongTra;
    private String hinhAnh;
    private String nguoiTao;
    private String nguoiSua;
    private Instant ngayTao;
    private Instant ngaySua;
    private String ghiChu;
    private String trangThai;
}
