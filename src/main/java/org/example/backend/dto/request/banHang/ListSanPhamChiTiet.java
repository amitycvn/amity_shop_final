package org.example.backend.dto.request.banHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.SanPhamChiTiet;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListSanPhamChiTiet {

    private UUID idHoaDon;
    private List<UUID> sanPhamChiTiet;
}
