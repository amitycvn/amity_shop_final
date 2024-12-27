package org.example.backend.dto.request.banHang;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.PhieuGiamGia;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BanHangRequest {


    private UUID id;


    private NguoiDung idNguoiDung;


    private PhieuGiamGia idPhieuGiamGia;

    private DotGiamGia idDotGiamGia;


    private String ma;

    private String soDienThoai;


    private String diaChi;

    private BigDecimal giaGoc;


    private BigDecimal giaGiam;

    private BigDecimal tongTien;

    private String email;

    private String loaiHoaDon;

    private String ghiChu;


    private String qrCode;

    private BigDecimal tienVanChuyen;


    private Instant ngayDuKienNhan;

    private Instant ngayNhanHang;

    private Instant ngayTraHang;

    private String nhanVien;


    private Instant ngayMua;

    private String nguoiTao;


    private String nguoiSua;

    private Instant ngayTao;


    private Instant ngaySua;


    private String trangThai;

    private Boolean deleted;
}
