package org.example.backend.dto.response.phieuGiamGia;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class phieuGiamGiaReponseAdmin {
    private UUID id;
    private UUID idNguoiDung;
    private String ma;
    private String ten;
    private Boolean loai;
    private BigDecimal giaTri;
    private BigDecimal giamToiDa;
    private String mucDo;
    private Instant ngayBatDau;
    private Instant ngayKetThuc;
    private Integer soLuong;
    private Integer dieuKien;
    private String trangThai;
}
