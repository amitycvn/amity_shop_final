package org.example.backend.dto.request.sanPham;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class SanPhamChiTietSearchRequest2 {
    private String kichThuoc;
    private String mauSac;
    private String tenSanPhamChiTiet; // Tên sản phẩm chi tiết
    private String trangThai;        // Trạng thái


    public SanPhamChiTietSearchRequest2(String kichThuoc, String mauSac, String tenSanPhamChiTiet, String trangThai) {
        this.kichThuoc = kichThuoc;
        this.mauSac = mauSac;
        this.tenSanPhamChiTiet = tenSanPhamChiTiet;
        this.trangThai = trangThai;
    }

    public SanPhamChiTietSearchRequest2() {
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
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
