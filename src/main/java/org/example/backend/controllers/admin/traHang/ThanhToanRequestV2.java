package org.example.backend.controllers.admin.traHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.HoaDon;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThanhToanRequestV2 {
    private UUID idHoaDon;

    private String phuongThuc;

    private BigDecimal tongTien;

    private String moTa;

}
