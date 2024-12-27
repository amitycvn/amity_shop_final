package org.example.backend.dto.request.nhanVien;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

import static org.example.backend.constants.Constant.NHAN_VIEN;
import static org.example.backend.constants.Constant.TRANG_THAI_USER;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class NhanVienRequestAdd {

    private UUID id;

    private String ma;

    private String email;

    private String sdt;

    private String matKhau="a1234b5c";

    private String ten;

    private String diaChi;

    private Instant ngaySinh;

    private String gioiTinh;

    private String hinhAnh;

    private String cccd;

    private Integer diem;

    private String nguoiTao;

    private String nguoiSua;

    private Instant ngayTao;

    private Instant ngaySua;

    private String chucVu=NHAN_VIEN;

    private String trangThai=TRANG_THAI_USER;

    private Boolean deleted;
}
