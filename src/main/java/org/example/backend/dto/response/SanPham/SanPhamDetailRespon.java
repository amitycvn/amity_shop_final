package org.example.backend.dto.response.SanPham;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDetailRespon {
    private UUID id;
    private String ten;
    private String chatLieu;
    private String mauSac;
    private String kichThuoc;
    public String lopLot;
    public int soLuong;
    public BigDecimal giaBan;
    public BigDecimal giaNhap;
    public String trangThai;
    public String hinhAnh;
}
