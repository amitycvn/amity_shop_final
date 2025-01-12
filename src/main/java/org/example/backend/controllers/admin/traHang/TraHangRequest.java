package org.example.backend.controllers.admin.traHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.SanPhamChiTiet;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TraHangRequest {
    private UUID idHoaDon;
    private UUID idNguoiDung;
    private List<UUID> sanPhamChiTiet;
    private String lyDo;
}
