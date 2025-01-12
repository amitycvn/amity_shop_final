package org.example.backend.repositories;


import org.example.backend.dto.response.banHang.ThanhToanResponse;
import org.example.backend.models.HoaDon;
import org.example.backend.models.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ThanhToanRepository extends JpaRepository<ThanhToan, UUID> {
    @Query("""
        select new org.example.backend.dto.response.banHang.ThanhToanResponse(
            t.id, 
            t.idHoaDon, 
            t.phuongThuc, 
            t.tienMat, 
            t.tienChuyenKhoan, 
            t.tongTien, 
            t.ngayTao, 
            t.ngaySua, 
            t.trangThai, 
            t.deleted, 
            t.phuongThucVnp, 
            t.moTa, 
            t.nguoiTao, 
            t.nguoiSua
        )
        from ThanhToan t
        where t.deleted = false
""")
    List<ThanhToanResponse> getAllThanhToan();

    ThanhToan findByIdHoaDon(HoaDon idHoaDon);
}