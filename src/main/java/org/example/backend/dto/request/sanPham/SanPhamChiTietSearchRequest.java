package org.example.backend.dto.request.sanPham;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@ToString
@Builder

public class SanPhamChiTietSearchRequest {
    private UUID idSanPham;
    private String mauSac;
    private String kichThuoc;

    public UUID getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(UUID idSanPham) {
        this.idSanPham = idSanPham;
    }

    public SanPhamChiTietSearchRequest(UUID idSanPham, String mauSac, String kichThuoc) {
        this.idSanPham = idSanPham;

        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
    }

    public String getMauSac() {
        return mauSac;
    }

    public SanPhamChiTietSearchRequest() {
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }
}
