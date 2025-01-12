package org.example.backend.controllers.admin.traHang;

import org.example.backend.models.HoaDon;
import org.example.backend.models.HoaDonChiTiet;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.SanPham;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.models.ThanhToan;
import org.example.backend.models.TraHang;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.repositories.ThanhToanRepository;
import org.example.backend.repositories.TraHangRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TraHangControllerV2 {

    private final TraHangRepository traHangRepository;
    private final HoaDonRepository hoaDonRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final ThanhToanRepository thanhToanRepository;

    public TraHangControllerV2(TraHangRepository traHangRepository, HoaDonRepository hoaDonRepository, SanPhamChiTietRepository sanPhamChiTietRepository, NguoiDungRepository nguoiDungRepository, HoaDonChiTietRepository hoaDonChiTietRepository, ThanhToanRepository thanhToanRepository) {
        this.traHangRepository = traHangRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.thanhToanRepository = thanhToanRepository;
    }

    @GetMapping("api/v1/admin/product-return/all")
    public ResponseEntity<?> getAllTraHangAmin() {
        List<TraHang> traHangList = traHangRepository.findAll();
        List<TraHangReponse> traHangReponseList = new ArrayList<>();
        for (TraHang traHang : traHangList) {
            TraHangReponse traHangReponse = new TraHangReponse();
            traHangReponse.setId(traHang.getId());
            traHangReponse.setIdHoaDon(traHang.getIdHoaDon().getId());
            traHangReponse.setMaHoaDon(traHang.getIdHoaDon().getMa());
            traHangReponse.setSdt(traHang.getIdHoaDon().getSoDienThoai());
            traHangReponse.setLoaiHoaDon(traHang.getIdHoaDon().getLoaiHoaDon());
            traHangReponse.setIdNguoiDung(traHang.getIdNguoiDung().getId());
            traHangReponse.setTenNguoiDung(traHang.getIdNguoiDung().getTen());
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findAllByIdHoaDonAndIdSpct(traHang.getIdHoaDon(), traHang.getIdSanPhamChiTiet());
            traHangReponse.setIdHoaDonChiTiet(hoaDonChiTiet.getId());
            traHangReponse.setSoLuong(hoaDonChiTiet.getSoLuong());
            traHangReponse.setGia(hoaDonChiTiet.getGia());
            traHangReponse.setGiaGiam(hoaDonChiTiet.getGiaGiam());
            traHangReponse.setIdSpct(hoaDonChiTiet.getIdSpct().getId());
            traHangReponse.setTenspct(hoaDonChiTiet.getIdSpct().getTen());
            traHangReponse.setTrangThaiTraHang(traHang.getTrangThai());
            traHangReponse.setTrangThaiHoaDon(traHang.getIdHoaDon().getTrangThai());
            traHangReponse.setLyDo(traHang.getLyDo());
            traHangReponse.setNgayTao(traHang.getNgayTao());
            traHangReponse.setNgaySua(traHang.getNgaySua());
            traHangReponseList.add(traHangReponse);
        }
        return ResponseEntity.ok(traHangReponseList);
    }

    @PostMapping("api/v1/admin/product-return/create_return")
    public ResponseEntity<?> addTraHang(@RequestBody TraHangRequest traHangRequest) {

        // Lấy thông tin hóa đơn
        HoaDon hoaDon = hoaDonRepository.findById(traHangRequest.getIdHoaDon())
                .orElseThrow(() -> new RuntimeException("HoaDon Not Found"));

        // Kiểm tra thời gian tạo hóa đơn (kiểu Instant) có vượt quá 7 ngày không
        Instant ngayTao = hoaDon.getNgaySua(); // Kiểu Instant
        Instant hienTai = Instant.now(); // Lấy thời gian hiện tại
        if (Duration.between(ngayTao, hienTai).toDays() > 7) {
            return ResponseEntity.badRequest().body("Không thể tạo trả hàng vì hóa đơn đã quá 7 ngày kể từ ngày tạo.");
        }

        if (hoaDon.getLoaiHoaDon().equalsIgnoreCase("Bán hàng tại quầy")) {
            // Đối với "Bán hàng tại quầy", trạng thái phải là "Đã Thanh Toán"
            if (!hoaDon.getTrangThai().equalsIgnoreCase("Đã Thanh Toán")) {
                return ResponseEntity.badRequest().body("Trạng thái của hóa đơn là " + hoaDon.getTrangThai() + ". Chưa đủ điều kiện thực hiện trả hàng. (Đã Thanh Toán)");
            }
        } else if (hoaDon.getLoaiHoaDon().equalsIgnoreCase("Trực Tuyến")) {
            // Lấy thông tin thanh toán liên quan đến hóa đơn
            ThanhToan thanhToan = thanhToanRepository.findByIdHoaDon(hoaDon);
            if (thanhToan == null) {
                return ResponseEntity.badRequest().body("Không tìm thấy thông tin thanh toán cho hóa đơn: " + hoaDon.getId());
            }

            // Kiểm tra trạng thái dựa trên phương thức thanh toán
            if (thanhToan.getPhuongThuc().equalsIgnoreCase("Thanh toán khi nhận hàng")) {
                if (!hoaDon.getTrangThai().equalsIgnoreCase("Đã Thanh Toán")) {
                    return ResponseEntity.badRequest().body("Trạng thái của hóa đơn là " + hoaDon.getTrangThai() + ". Chưa đủ điều kiện thực hiện trả hàng. (Đã Thanh Toán)");
                }
            } else if (thanhToan.getPhuongThuc().equalsIgnoreCase("Thanh toán qua VNPay")) {
                if (!hoaDon.getTrangThai().equalsIgnoreCase("Đã Giao Hàng")) {
                    return ResponseEntity.badRequest().body("Trạng thái của hóa đơn là " + hoaDon.getTrangThai() + ". Chưa đủ điều kiện thực hiện trả hàng. (Đã Giao Hàng)");
                }
            } else {
                return ResponseEntity.badRequest().body("Phương thức thanh toán không hợp lệ: " + thanhToan.getPhuongThuc());
            }
        } else {
            // Nếu loại hóa đơn không hợp lệ
            return ResponseEntity.badRequest().body("Loại hóa đơn không hợp lệ: " + hoaDon.getLoaiHoaDon());
        }


        // Lấy thông tin người dùng
        NguoiDung nguoiDung = nguoiDungRepository.findById(traHangRequest.getIdNguoiDung())
                .orElseThrow(() -> new RuntimeException("NguoiDung Not Found"));

        // Kiểm tra người dùng có sở hữu hóa đơn này không
        if (!hoaDon.getIdNguoiDung().getId().equals(nguoiDung.getId())) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại đơn hàng này.");
        }

        // Duyệt danh sách sản phẩm trong yêu cầu trả hàng
        for (UUID sanPhamChiTiet : traHangRequest.getSanPhamChiTiet()) {
            SanPhamChiTiet sanPhamChiTiet1 = sanPhamChiTietRepository.findById(sanPhamChiTiet)
                    .orElseThrow(() -> new RuntimeException("SanPhamChiTiet Not Found"));

            // Kiểm tra sản phẩm có thuộc hóa đơn không
            HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.findAllByIdHoaDonAndIdSpct(hoaDon, sanPhamChiTiet1);
            if (!hoaDonChiTiet.getIdSpct().getId().equals(sanPhamChiTiet1.getId())) {
                throw new RuntimeException("Sản phẩm không thuộc đơn hàng này.");
            }

            // Kiểm tra đã tồn tại trả hàng cho sản phẩm này hay chưa
            TraHang traHang = traHangRepository.findTraHangByIdNguoiDungAndIdHoaDonAndIdSanPhamChiTiet(nguoiDung, hoaDon, sanPhamChiTiet1);
            if (traHang != null) {
                return ResponseEntity.badRequest().body("Đã tồn tại trả hàng cho sản phẩm " + sanPhamChiTiet1.getTen() + " trong hóa đơn " + hoaDon.getId());
            }
        }

        // Lưu trả hàng cho từng sản phẩm
        for (UUID sanPhamChiTiet : traHangRequest.getSanPhamChiTiet()) {
            TraHang traHang = new TraHang();
            traHang.setIdHoaDon(hoaDon);
            traHang.setIdSanPhamChiTiet(sanPhamChiTietRepository.findById(sanPhamChiTiet).orElseThrow());
            traHang.setIdNguoiDung(nguoiDung);
            traHang.setLyDo(traHangRequest.getLyDo());
            traHang.setTrangThai("Đang xử lý trả hàng");
            traHangRepository.save(traHang);
        }

        return ResponseEntity.ok(traHangRepository.findAll());
    }




    @PutMapping("api/v1/admin/product-return/update-status")
    public ResponseEntity<?> updateStatusTraHang(@RequestBody TraHangRequestStatus traHangRequestStatus) {
        for (UUID th: traHangRequestStatus.getIdTraHang()){
            TraHang traHang = traHangRepository.findById(th).orElseThrow(()-> new RuntimeException("TraHang Not Found"));
            traHang.setTrangThai(traHangRequestStatus.getTrangThai());
            traHangRepository.save(traHang);
        }
        return ResponseEntity.ok(traHangRepository.findAll());
    }
}
