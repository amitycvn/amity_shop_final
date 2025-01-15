package org.example.backend.controllers.admin.banHang;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BanHangClientResponse {
    private UUID idSpct;
    private String tenSp;
    private String tenSpct;
    private String tenMauSac;
    private String tenKichThuoc;
    private String tenHang;
    private String tenDanhMuc;
    private Integer soLuong;
    private UUID dotGiamGia;
    private Boolean loaiGiamGia;
    private BigDecimal giaTriGiam;
    private BigDecimal giaBan;
    private BigDecimal giaGiam;
    private BigDecimal giaSauGiam;
    private String hinhAnh;
    private String moTa;
    private String trangThai;
    private Instant ngayTao;
}
