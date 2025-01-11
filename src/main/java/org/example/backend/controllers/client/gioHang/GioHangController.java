package org.example.backend.controllers.client.gioHang;

import lombok.RequiredArgsConstructor;

import org.example.backend.dto.request.gioHang.GioHangChiTietRequest;
import org.example.backend.dto.response.gioHang.GioHangChiTietResponse;
import org.example.backend.dto.response.sanPhamV2.SanPhamChiTietResponse;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.services.GioHangService;
import org.example.backend.services.SanPhamChiTietService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class GioHangController {
    private final GioHangService gioHangService;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<GioHangChiTietResponse>> getGioHang(@PathVariable UUID userId) {
        List<GioHangChiTietResponse> gioHangChiTietResponses = new ArrayList<>();
        String trangThai = "Hoạt động";
        for (GioHangChiTietResponse gioHangChiTietResponse : gioHangService.getGioHang(userId)) {
            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(gioHangChiTietResponse.getIdSanPhamChiTiet()).orElse(null);
            if(sanPhamChiTiet != null && sanPhamChiTiet.getTrangThai().equals(trangThai) && sanPhamChiTiet.getIdSanPham().getTrangThai().equals(trangThai)){
                gioHangChiTietResponses.add(gioHangChiTietResponse);
            }else{
                gioHangService.deleteFromCart(userId, gioHangChiTietResponse.getId());
            }
        }
        return ResponseEntity.ok(gioHangChiTietResponses);
    }

    @PostMapping("/{userId}/them-san-pham")
    public ResponseEntity<GioHangChiTietResponse> addToCart(
            @PathVariable UUID userId,
            @RequestBody GioHangChiTietRequest request) {
        return ResponseEntity.ok(gioHangService.addToCart(userId, request));
    }

    @PostMapping("/{userId}/update-san-pham")
    public ResponseEntity<GioHangChiTietResponse> updateToCart(
            @PathVariable UUID userId,
            @RequestBody GioHangChiTietRequest request) {
        return ResponseEntity.ok(gioHangService.updateToCart(userId, request));
    }

    @DeleteMapping("/{userId}/xoa-san-pham/{gioHangChiTietId}")
    public ResponseEntity<Void> deleteFromCart(
            @PathVariable UUID userId,
            @PathVariable UUID gioHangChiTietId) {
        gioHangService.deleteFromCart(userId, gioHangChiTietId);
        return ResponseEntity.ok().build();
    }
}