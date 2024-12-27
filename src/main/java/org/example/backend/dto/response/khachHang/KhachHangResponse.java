package org.example.backend.dto.response.khachHang;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhachHangResponse {
    private UUID id;
    private String ma;
    private String email;
    private String sdt;
    private String matKhau;
    private String ten;
    private String diaChi;
    private Instant ngaySinh;
    private String gioiTinh;
    private String hinhAnh;
    private Integer diem;
    private String trangThai;
    private Boolean deleted;
    private String chucVu;

}
