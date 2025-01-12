package org.example.backend.controllers.admin.diaChi;


import org.example.backend.dto.request.diaChi.DiaChiRequet;
import org.example.backend.models.DiaChi;
import org.example.backend.repositories.DiaChiRepository;
import org.example.backend.services.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class DiaChiController {
    @Autowired
    private DiaChiService diaChiService;

    // Lấy danh sách địa chỉ theo người dùng
//    @GetMapping("/nguoidung/{idNguoiDung}")
//    public ResponseEntity<List<DiaChi>> getDiaChiByNguoiDung(@PathVariable UUID idNguoiDung) {
//        List<DiaChi> diaChiList = diaChiService.getDiaChiByNguoiDung(idNguoiDung);
//        return ResponseEntity.ok(diaChiList);
//    }

    // Tạo mới địa chỉ
    @PostMapping("/admin/user/diaChi/create")
    public ResponseEntity<?> createDiaChi(@RequestBody DiaChiRequet diaChiRequest) {
        try {
            DiaChi newDiaChi = diaChiService.createDiaChi(diaChiRequest);
            return ResponseEntity.ok(newDiaChi);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Người dùng không hợp le");
        }
    }

    // Cập nhật địa chỉ
    @PutMapping("/admin/user/diaChi/update/{id}")
    public ResponseEntity<?> updateDiaChi(
            @PathVariable UUID id,
            @RequestBody DiaChiRequet diaChiRequest
    ) {
        Optional<DiaChi> updatedDiaChi = diaChiService.updateDiaChi(id, diaChiRequest);
        return updatedDiaChi.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
