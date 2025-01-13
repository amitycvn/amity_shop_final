package org.example.backend.controllers.admin.banHang;

import org.example.backend.constants.api.Admin;
import org.example.backend.controllers.admin.traHang.ThanhToanRequestV2;
import org.example.backend.dto.request.banHang.ThanhToanRequest;
import org.example.backend.models.HoaDon;
import org.example.backend.models.ThanhToan;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.repositories.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThanhToanController {
    @Autowired
    ThanhToanRepository thanhToanRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @GetMapping(Admin.PAY_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(thanhToanRepository.getAllThanhToan());
    }

    @PostMapping(Admin.PAY_CREATE)
    public ResponseEntity<?> create(@RequestBody ThanhToanRequest thanhToanRequest) {
        ThanhToan t = new ThanhToan();
        t.setIdHoaDon(thanhToanRequest.getIdHoaDon());
        t.setPhuongThuc(thanhToanRequest.getPhuongThuc());
        t.setTienMat(thanhToanRequest.getTienMat());
        t.setTienChuyenKhoan(thanhToanRequest.getTienChuyenKhoan());
        t.setTongTien(thanhToanRequest.getTongTien());
        t.setNgayTao(thanhToanRequest.getNgayTao());
        t.setNgaySua(thanhToanRequest.getNgaySua());
        t.setTrangThai(thanhToanRequest.getTrangThai());
        t.setPhuongThucVnp(thanhToanRequest.getPhuongThucVnp());
        t.setMoTa(thanhToanRequest.getMoTa());
        t.setNguoiTao(thanhToanRequest.getNguoiTao());
        t.setNguoiSua(thanhToanRequest.getNguoiSua());
        return ResponseEntity.ok(thanhToanRepository.save(t));
    }

    @PostMapping(Admin.PAY_CREATE_V2)
    public ResponseEntity<?> createThanhToanTraHang(@RequestBody ThanhToanRequestV2 thanhToanRequest) {
        ThanhToan t = new ThanhToan();
        HoaDon hoaDon = hoaDonRepository.findById(thanhToanRequest.getIdHoaDon()).orElseThrow(()-> new RuntimeException("Lỗi tạo thanh toán cho hóa đơn"));
        t.setIdHoaDon(hoaDon);
        t.setPhuongThuc(thanhToanRequest.getPhuongThuc());
        t.setTongTien(thanhToanRequest.getTongTien());
        return ResponseEntity.ok(thanhToanRepository.save(t));
    }
}
