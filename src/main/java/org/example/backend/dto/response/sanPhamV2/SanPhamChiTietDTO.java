package org.example.backend.dto.response.sanPhamV2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {
    private UUID id;
    private String tenSanPham;
    private String mauSac;
    private String kichThuoc;
    private BigDecimal giaBan;
    private Integer soLuong;
    private String hinhAnh;
}
