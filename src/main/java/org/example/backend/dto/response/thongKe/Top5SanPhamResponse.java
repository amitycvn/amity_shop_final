package org.example.backend.dto.response.thongKe;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Top5SanPhamResponse {
    private UUID idHDCT;
    private String ten;
    private Long tongSoLuong;
}
