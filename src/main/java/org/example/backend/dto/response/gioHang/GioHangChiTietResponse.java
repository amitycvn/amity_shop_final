package org.example.backend.dto.response.gioHang;

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
public class GioHangChiTietResponse {
    private UUID id;
    private UUID idGioHang;
    private UUID idSanPhamChiTiet;
    
    private String tenSp;
    private String mauSac;
    private String kichThuoc;
    private String hinhAnh;
    private BigDecimal donGia;
    
    private Integer soLuong;
    private BigDecimal thanhTien;

    private Integer soLuongTon;
}
