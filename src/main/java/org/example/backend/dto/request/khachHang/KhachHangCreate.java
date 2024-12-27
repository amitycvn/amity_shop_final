package org.example.backend.dto.request.khachHang;

import lombok.*;

import java.time.Instant;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhachHangCreate {
    private String ma;
    private String email;
    private String sdt;
    private String matKhau="a123456";
    private String ten;
    private String diaChi;
    private Instant ngaySinh;
    private String gioiTinh;
    private String hinhAnh;
    private String trangThai;
    private Integer diem;
    private String chucVu="khachhang";

}
