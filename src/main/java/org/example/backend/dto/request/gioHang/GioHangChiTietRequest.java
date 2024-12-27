package org.example.backend.dto.request.gioHang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietRequest {
    private UUID idSanPhamChiTiet;
    private Integer soLuong;
    private BigDecimal giaBan;
}
