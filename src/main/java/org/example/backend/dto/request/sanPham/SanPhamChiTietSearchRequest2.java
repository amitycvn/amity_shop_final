package org.example.backend.dto.request.sanPham;

import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamChiTietSearchRequest2 {
    private String kichThuoc;
    private String mauSac;
    private String tenSanPham; // Tên sản phẩm chi tiết
    private String trangThai;        // Trạng thái



}
