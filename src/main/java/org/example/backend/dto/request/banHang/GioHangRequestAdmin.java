package org.example.backend.dto.request.banHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.SanPhamChiTiet;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHangRequestAdmin {
    private SanPhamChiTiet idSpct;

    private int soLuong;

//    private BigDecimal giaBan;

}
