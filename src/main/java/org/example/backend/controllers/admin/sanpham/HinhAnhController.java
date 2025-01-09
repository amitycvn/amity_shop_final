

package org.example.backend.controllers.admin.sanpham;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.HinhAnhRequest;
import org.example.backend.dto.response.SanPham.HinhAnhRespon;
import org.example.backend.models.HinhAnh;
import org.example.backend.models.SanPham;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.HinhAnhRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController

public class HinhAnhController {
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private Cloudinary cloudinary;

//    @PostMapping(Admin.IMAGE_CREATE)
//    public ResponseEntity<?> addHinhAnh(@RequestBody HinhAnhRequest hinhAnhRequest) {
//        try {
//            // Check if the associated product detail exists
//            UUID sanPhamId = hinhAnhRequest.getIdSanPham().getId();
//            SanPham sanPham = sanPhamRepository.findById(sanPhamId)
//                    .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại với ID: " + sanPhamId));
//
//            // Create new HinhAnh entity
//            HinhAnh hinhAnh = HinhAnh.builder()
//                    .ma(hinhAnhRequest.getMa())
//                    .ten(hinhAnhRequest.getTen())
//                    .url(hinhAnhRequest.getUrl())
//                    .nguoiTao(hinhAnhRequest.getNguoiTao())
//                    .nguoiSua(hinhAnhRequest.getNguoiSua())
//                    .trangThai(hinhAnhRequest.getTrangThai())
//                    .idsp(sanPham)
//                    .build();
//
//            // Save to database
//            HinhAnh savedHinhAnh = hinhAnhRepository.save(hinhAnh);
//
//            return ResponseEntity.ok("Thêm hình ảnh thành công! ID: " + savedHinhAnh.getId());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Lỗi khi thêm hình ảnh: " + e.getMessage());
//        }
//    }

    @PostMapping(value = Admin.IMAGE_CREATE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addHinhAnh(
            @RequestParam("idSanPham") UUID idSanPham,
            @RequestParam("ma") String ma,
            @RequestParam("ten") String ten,
            @RequestParam("nguoiTao") String nguoiTao,
            @RequestParam("nguoiSua") String nguoiSua,
            @RequestParam("trangThai") String trangThai,
            @RequestParam("hinhAnh") MultipartFile hinhAnhFile) {

        try {
            // Check if the associated product detail exists
            SanPham sanPham = sanPhamRepository.findById(idSanPham)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với ID: " + idSanPham));

            // Upload image to Cloudinary (or any file storage service)
            Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnhFile.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");

            // Create new HinhAnh entity
            HinhAnh hinhAnh = HinhAnh.builder()
                    .ma(ma)
                    .ten(ten)
                    .url(imageUrl)
                    .nguoiTao(nguoiTao)
                    .nguoiSua(nguoiSua)
                    .trangThai(trangThai)
                    .idsp(sanPham)
                    .build();

            // Save to database
            HinhAnh savedHinhAnh = hinhAnhRepository.save(hinhAnh);

            return ResponseEntity.ok("Thêm hình ảnh thành công! ID: " + savedHinhAnh.getId());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Tải lên hình ảnh thất bại: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi thêm hình ảnh: " + e.getMessage());
        }
    }


    // 1. Hiển thị tất cả hình ảnh

    @GetMapping(Admin.IMAGE_GET_ALL)
    public ResponseEntity<?> getAllImagesGroupedByProduct() {
        try {
            // Lấy danh sách tất cả hình ảnh từ repository
            List<HinhAnhRespon> images = hinhAnhRepository.getAll();

            // Nhóm các hình ảnh theo idSanPham
            Map<UUID, List<HinhAnhRespon>> groupedImages = images.stream()
                    .collect(Collectors.groupingBy(HinhAnhRespon::getIdSp));

            // Trả về danh sách được nhóm
            return ResponseEntity.ok(groupedImages);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            return ResponseEntity.badRequest().body("Lỗi khi lấy danh sách hình ảnh: " + e.getMessage());
        }
    }

    // 2. Lấy hình ảnh theo id sản phẩm
    @GetMapping(Admin.IMAGE_GET_BY_ID)
    public ResponseEntity<?> getImagesByProductId(@PathVariable UUID idSanPham) {
        try {
            List<HinhAnhRespon> images = hinhAnhRepository.findByIdspId(idSanPham);
            if (images.isEmpty()) {
                return ResponseEntity.ok("Không có hình ảnh nào cho sản phẩm với ID: " + idSanPham);
            }
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi lấy hình ảnh: " + e.getMessage());
        }
    }

}

