package org.example.backend.controllers.admin.traHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TraHangReponse {
    private UUID id;
    private UUID idHoaDon;
    private String maHoaDon;
    private String sdt;
    private String loaiHoaDon;
    private UUID idNguoiDung;
    private String tenNguoiDung;
    private UUID idHoaDonChiTiet;
    private Integer soLuong;
    private BigDecimal gia;
    private BigDecimal giaGiam;
    private UUID idSpct;
    private String tenspct;
    private String trangThaiTraHang;
    private String trangThaiHoaDon;
    private Instant ngayTao;
    private Instant ngaySua;
    private String lyDo;
    private String hinhAnh;
}
