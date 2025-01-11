package org.example.backend.dto.request.banHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTietRequestV2 {
    private UUID idSpct;
    private Integer soLuong;
    private BigDecimal gia;
    private BigDecimal giaGiam;
    private String trangThai;
}
