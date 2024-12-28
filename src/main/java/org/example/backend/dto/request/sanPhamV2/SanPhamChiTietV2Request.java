package org.example.backend.dto.request.sanPhamV2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class SanPhamChiTietV2Request {
    private String tenSp;
    private UUID idChatLieu;
    private UUID idLopLot;
    private UUID idHang;
    private UUID idDeGiay;
    private UUID idDanhMuc;
    private List<UUID> mauSacIds;
    private List<UUID> kichThuocIds;
    private BigDecimal giaBan;
    private BigDecimal giaNhap;
    private Integer soLuong;
    private String trangThai;
    private String moTa;
}
