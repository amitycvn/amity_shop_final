package org.example.backend.controllers.admin.traHang;

import org.example.backend.constants.Status;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.traHang.traHangRequest;
import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
import org.example.backend.dto.response.traHang.TraHangResponse;
import org.example.backend.models.HoaDon;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.models.TraHang;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.repositories.TraHangRepository;
import org.example.backend.services.TraHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//
//import static org.example.backend.constants.api.Admin.*;
//
@RestController
public class traHangController {
    @Autowired
    TraHangRepository traHangRepository;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    private TraHangService traHangService;

//    @PostMapping(Admin.PRODUCT_RETURN_CREATE_CLIENT)
//    public ResponseEntity<?> traHang(@RequestBody traHangRequest request) {
//        // 1. Tìm sản phẩm chi tiết
//        Optional<SanPhamChiTiet> optionalSanPhamChiTiet = sanPhamChiTietRepository.findById(request.getId());
//        if (optionalSanPhamChiTiet.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sản phẩm chi tiết");
//        }
//        SanPhamChiTiet sanPhamChiTiet = optionalSanPhamChiTiet.get();
//
//        // 2. Tạo phiếu trả hàng
//        TraHang traHang = new TraHang();
////        traHang.setIdSpct(sanPhamChiTiet);
////        traHang.setSoLuong(request.getSoLuong());
////        traHang.setLyDo(request.getLyDo());
////        traHang.setNguoiTao(request.getNguoiTao());
//        traHang.setTrangThai(Status.DANG_XU_LY);
//        traHangRepository.save(traHang);
//
//        // 3. Cập nhật số lượng trong SanPhamChiTiet
//        sanPhamChiTiet.setSoLuongTra(
//                sanPhamChiTiet.getSoLuongTra() != null ?
//                        sanPhamChiTiet.getSoLuongTra() + request.getSoLuong() :
//                        request.getSoLuong()
//        );
//        sanPhamChiTietRepository.save(sanPhamChiTiet);
//
//        return ResponseEntity.ok("Tạo phiếu trả hàng thành công");
//    }
//
//    @GetMapping("/api/v1/admin/product-return/all")
//    public ResponseEntity<?> getAllListTraHang() {
//        List<TraHang> listTraHang = traHangRepository.findAll();
//        return ResponseEntity.ok(listTraHang);
//    }
//
//    @PutMapping(Admin.PRODUCT_RETURN_UPDATE)
//    public ResponseEntity<?> updateTraHangStatus_client( @RequestBody traHangRequest request) {
//        Optional<TraHang> optionalTraHang = traHangRepository.findById(request.getId());
//        if (optionalTraHang.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy phiếu trả hàng");
//        }
//
//        TraHang traHang = optionalTraHang.get();
//        traHang.setTrangThai(request.getTrangThai());
////        traHang.setNguoiSua(request.getNguoiSua());
//        traHangRepository.save(traHang);
//
//        return ResponseEntity.ok("Cập nhật trạng thái thành công");
//    }
//
//    @GetMapping(Admin.PRODUCT_RETURN_GET_ALL_CLIENT)
//    public ResponseEntity<?> getAllTraHang(@RequestParam String email) {
//
//        List<TraHangResponse> listTraHang = traHangRepository.getAllTraHangClient(email);
//        return ResponseEntity.ok(listTraHang);
//    }
//
// //    // API để lấy hóa đơn theo ID khách hàng
//     @GetMapping(Admin.PRODUCT_RETURN_GET_BY_IDKH)
//     public ResponseEntity<List<hoaDonChiTietReponse>> getHoaDonByIdNguoiDung(@PathVariable UUID idNguoiDung) {
//         List<hoaDonChiTietReponse> hoaDonChiTietList = traHangService.getHoaDonByIdNguoiDung(idNguoiDung);
//         if (hoaDonChiTietList.isEmpty()) {
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.ok(hoaDonChiTietList);
//      }
//     @GetMapping(Admin.PRODUCT_RETURN_DETAIL_BY_ID)
//     public ResponseEntity<List<hoaDonChiTietReponse>> getHoaDonCtById(@PathVariable UUID id) {
//         List<hoaDonChiTietReponse> hoaDonChiTietList = traHangService.getHoaDonCtById(id);
//         if (hoaDonChiTietList.isEmpty()) {
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.ok(hoaDonChiTietList);
//     }

}
