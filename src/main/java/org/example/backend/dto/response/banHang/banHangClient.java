package org.example.backend.dto.response.banHang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class banHangClient
 {
    private UUID idSpct;
    private String tenSp;
    private String tenSpct;
    private String tenMauSac;
    private String tenKichThuoc;
    private String tenDanhMuc;
    private String tenHang;
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
