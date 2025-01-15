package org.example.backend.dto.request.banHang;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HoaDonChiTietRequestV2 {
    private UUID idSpct;
    private Integer soLuong;
    private BigDecimal gia;
    private BigDecimal giaGiam;
    private String trangThai;
}
