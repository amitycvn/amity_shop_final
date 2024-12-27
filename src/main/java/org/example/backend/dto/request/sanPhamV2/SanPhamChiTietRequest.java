package org.example.backend.dto.request.sanPhamV2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class SanPhamChiTietRequest {
    private UUID idSanPham;
    private List<UUID> mauSacIds;
    private List<UUID> kichThuocIds;
    private UUID hang;
    private UUID deGiay;
    private UUID danhMuc;
    private BigDecimal giaBan;
    private Integer soLuong;
    private String trangThai;
    private String moTa;
}
