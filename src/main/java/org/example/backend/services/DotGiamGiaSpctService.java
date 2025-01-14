package org.example.backend.services;

import org.example.backend.constants.Status;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.DotGiamGiaSpct;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.example.backend.repositories.DotGiamGiaSpctRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DotGiamGiaSpctService extends GenericServiceImpl<DotGiamGiaSpct, UUID> {
    public DotGiamGiaSpctService(JpaRepository<DotGiamGiaSpct, UUID> repository) {
        super(repository);
    }

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;

    @Autowired
    private DotGiamGiaSpctRepository dotGiamGiaSpctRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    public ResponseEntity<?> createDotGiamGiaWithSpct(DotGiamGia dotGiamGia, List<UUID> spctIds) {
        try {
            // Danh sách sản phẩm chi tiết đã tồn tại
            List<SanPhamChiTiet> existingSpcts = new ArrayList<>();

            for (UUID spctId : spctIds) {
                // Kiểm tra xem cặp idDotGiamGia và idSpct đã tồn tại chưa
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(spctId).orElse(null);
                boolean exists = dotGiamGiaSpctRepository.existsByIdDotGiamGiaAndIdSpct(dotGiamGia, sanPhamChiTiet);
                if (exists) {
                    // Thêm sản phẩm vào danh sách lỗi
                    SanPhamChiTiet spct = sanPhamChiTietRepository.findById(spctId)
                            .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại: " + spctId));
                    existingSpcts.add(spct);
                    continue;
                }

                // Nếu chưa tồn tại, lưu mới DotGiamGiaSpct
                SanPhamChiTiet spct = sanPhamChiTietRepository.findById(spctId)
                        .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại: " + spctId));

                DotGiamGiaSpct dotGiamGiaSpct = new DotGiamGiaSpct();
                dotGiamGiaSpct.setIdDotGiamGia(dotGiamGia);
                dotGiamGiaSpct.setIdSpct(spct);
                dotGiamGiaSpctRepository.save(dotGiamGiaSpct);
            }

            // Nếu có sản phẩm đã tồn tại, trả về danh sách
            if (!existingSpcts.isEmpty()) {
                List<String> existingSpctNames = new ArrayList<>();
                for (SanPhamChiTiet spct : existingSpcts) {
                    existingSpctNames.add(spct.getTen());
                }
                String existingSpctNamesString = String.join(", ", existingSpctNames);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Các sản phẩm đã tồn tại đợt giảm giá này"+existingSpctNamesString);
            }

            // Trả về kết quả thành công
            return ResponseEntity.ok(dotGiamGia);
        } catch (RuntimeException e) {
            // Trả về lỗi nếu sản phẩm không tồn tại
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            // Trả về lỗi không mong muốn khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }


    public void setDeletedDotGiamGiaSpctById(Boolean deleted,UUID id){
        dotGiamGiaSpctRepository.setDeletedDGGSpct(deleted, id);
    }


}
