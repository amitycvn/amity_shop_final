package org.example.backend.dto.response.banHang;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.example.backend.dto.request.banHang.HoaDonChiTietRequestV2;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.PhieuGiamGia;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class thongTinHoaDon {
    private List<HoaDonChiTietRequestV2> listHoaDonChiTiet;
    private NguoiDung idNguoiDung;
    private PhieuGiamGia idPhieuGiamGia;
    private String soDienThoai;
    private String diaChi;
    private BigDecimal giaGoc;
    private BigDecimal tongTien;
    private String loaiHoaDon;
    private BigDecimal tienVanChuyen;
}
