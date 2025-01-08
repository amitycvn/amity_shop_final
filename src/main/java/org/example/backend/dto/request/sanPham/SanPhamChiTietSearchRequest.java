package org.example.backend.dto.request.sanPham;

import lombok.Builder;
import lombok.ToString;

import java.util.UUID;

@ToString
@Builder

public class SanPhamChiTietSearchRequest {
    private UUID idSanPham;
    private String mauSac;
    private String kichThuoc;
    private String tenSanPhamChiTiet; // Thêm trường này
    private String trangThai;  // Thêm trường này


    public SanPhamChiTietSearchRequest() {
    }

    public SanPhamChiTietSearchRequest(UUID idSanPham, String mauSac, String kichThuoc, String tenSanPham, String trangThai) {
        this.idSanPham = idSanPham;
        this.mauSac = mauSac;
        this.kichThuoc = kichThuoc;
        this.tenSanPhamChiTiet = tenSanPham;
        this.trangThai = trangThai;
    }

    public UUID getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(UUID idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMauSac() {
        return mauSac;
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

    public String getTenSanPhamChiTiet() {
        return tenSanPhamChiTiet;
    }

    public void setTenSanPhamChiTiet(String tenSanPhamChiTiet) {
        this.tenSanPhamChiTiet = tenSanPhamChiTiet;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}