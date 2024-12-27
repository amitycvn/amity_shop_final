package org.example.backend.dto.request.sanPham;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.SanPhamChiTiet;

import java.time.Instant;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HinhAnhRequest {
    private String ma;


    private String ten;

    private String url;


    private Instant ngayTao;


    private Instant ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private String trangThai;

    private SanPhamChiTiet idSanPhamChiTiet;


}
