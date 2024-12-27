package org.example.backend.dto.response.banHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.HoaDon;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThanhToanResponse {
    private UUID id;

    private HoaDon idHoaDon;

    private String phuongThuc;

    private BigDecimal tienMat;

    private BigDecimal tienChuyenKhoan;

    private BigDecimal tongTien;

    private Instant ngayTao;

    private Instant ngaySua;

    private String trangThai;

    private Boolean deleted;

    private String phuongThucVnp;

    private String moTa;

    private String nguoiTao;

    private String nguoiSua;
}
