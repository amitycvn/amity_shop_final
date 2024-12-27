package org.example.backend.dto.response.dotGiamGia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.backend.models.SanPhamChiTiet;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class DotGiamGiaSpctResponse {
    private UUID id;
    private UUID idSpct;
    private UUID idDotGiamGia;




}
