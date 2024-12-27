package org.example.backend.dto.response.quanLyDonHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class hoaDonChiTietReponse {
    private UUID id;

    private UUID idSPCT;

    private UUID idHD;

    private String tenSanPham;

    private String mauSac;

    private String kichThuoc;

    private int soLuong;

    private BigDecimal gia;

    private String hinhAnh;

    private Instant ngayTao;

    private Boolean deleted;
}
