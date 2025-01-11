package org.example.backend.dto.response.banHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class gioHangResponseAdmin {
    private UUID idGioHang;
    private UUID idHoaDon;
    private UUID idSpct;
    private String tenSp;
    private int soLuong;
    private BigDecimal kichThuoc;
    private String mauSac;
    private BigDecimal giaBan;
    private String hinhAnh;
}
