package org.example.backend.controllers.admin.banHang;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.banHang.*;
import org.example.backend.dto.response.banHang.TrangThaiRespon;
import org.example.backend.dto.response.banHang.banHangClientResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.*;
import org.example.backend.repositories.*;
import org.example.backend.services.DotGiamGiaSpctService;
import org.example.backend.services.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
//import net.glxn.qrgen.javase.QRCode;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.Status.CHO_THANH_TOAN;
import static org.example.backend.constants.Status.CHO_XAC_NHAN_HOA_DON;

@RestController
public class BanHangController {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    PhieuGiamGiaRepository phieuGiamGiaRepository;
    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    VietQrService vietQrService;
    @Autowired
    private DotGiamGiaSpctService dotGiamGiaSpctService;
    @Autowired
    private DotGiamGiaSpctRepository dotGiamGiaSpctRepository;

    //    @GetMapping(Admin.SELL_GET_ALL)
//    public ResponseEntity<?> getAll() {
//        return ResponseEntity.ok(hoaDonRepository.getAllBanHang(CHO_XAC_NHAN_HOA_DON));
//    }
    @GetMapping(Admin.SELL_GET_ALL)
    public ResponseEntity<?> getAll() {
        List<String> trangThais = List.of(CHO_XAC_NHAN_HOA_DON, CHO_THANH_TOAN);
        return ResponseEntity.ok(hoaDonRepository.getAllBanHang(trangThais));
    }


//    @PostMapping(Admin.SELL_CREATE)
//    public ResponseEntity<?> create() {
//
//        TrangThaiRespon trangThaiRespon = hoaDonRepository.getAllTrangThai(CHO_XAC_NHAN_HOA_DON).orElse(null);
//        System.out.println("lol"+ trangThaiRespon);
//        if(trangThaiRespon == null || trangThaiRespon.getCount() <5) {
//            System.out.println("lol thuong"+ trangThaiRespon);
//            HoaDon hoaDon = new HoaDon();
//            return ResponseEntity.ok(hoaDonRepository.save(hoaDon));
//        }
//        return null;
//
//    }
@PostMapping(Admin.SELL_CREATE)
public ResponseEntity<?> create() {
    List<String> trangThais = List.of(CHO_THANH_TOAN, CHO_XAC_NHAN_HOA_DON);
    List<TrangThaiRespon> trangThaiRespon = hoaDonRepository.getAllTrangThai(trangThais);
    long totalCount = trangThaiRespon.stream()
            .mapToLong(TrangThaiRespon::getCount)
            .sum();
    if(trangThaiRespon == null || totalCount <5) {
        System.out.println("lol thuong"+ trangThaiRespon);
        HoaDon hoaDon = new HoaDon();
        return ResponseEntity.ok(hoaDonRepository.save(hoaDon));
    }
    return null;

}

    @PostMapping(Admin.SELL_CLIENT_CREATE)
    public ResponseEntity<?> createSellClient() {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setTrangThai("Chờ Xác Nhận");
            return ResponseEntity.ok(hoaDonRepository.save(hoaDon));
    }

    @PostMapping(Admin.SELL_DETAIL_CREATE)
    public ResponseEntity<?> detailCreate(@RequestBody List<HoaDonChiTietRequest> list) {
        try {
            if (list.size() > 0) {
                for (HoaDonChiTietRequest hdct : list) {
                    // Tìm hóa đơn và sản phẩm chi tiết
                    HoaDon hd = hoaDonRepository.findById(hdct.getIdHoaDon()).orElse(null);
                    SanPhamChiTiet spct = sanPhamChiTietRepository.findById(hdct.getIdSpct()).orElse(null);

                    if (hd != null && spct != null) {
                        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                        hoaDonChiTiet.setIdHoaDon(hd);
                        hoaDonChiTiet.setIdSpct(spct);
                        hoaDonChiTiet.setSoLuong(hdct.getSoLuong());
                        hoaDonChiTiet.setGia(hdct.getGia());
                        hoaDonChiTiet.setTrangThai(hdct.getTrangThai());

                        // Tìm tất cả các đợt giảm giá đang hoạt động
                        List<DotGiamGiaSpct> discounts =
                                dotGiamGiaSpctRepository.findActiveDiscountsByProductDetail(hdct.getIdSpct());
                        System.out.println("discount: " + discounts);

                        BigDecimal giaGiamTotNhat = BigDecimal.ZERO;

                        // Tính toán đợt giảm giá tốt nhất
                        for (DotGiamGiaSpct discount : discounts) {
                            DotGiamGia dotGiamGia = discount.getIdDotGiamGia();
                            BigDecimal giaGiamHienTai;

                            if (!dotGiamGia.getLoai()) { // Giảm giá theo tiền mawjt (loai = false)
                                giaGiamHienTai = hdct.getGia().multiply(dotGiamGia.getGiaTri())
                                        .divide(BigDecimal.valueOf(100));
                            } else { // Giảm giá theo tiền mặt
                                giaGiamHienTai = dotGiamGia.getGiaTri();
                            }

                            // So sánh và chọn giá giảm lớn nhất
                            if (giaGiamHienTai.compareTo(giaGiamTotNhat) > 0) {
                                giaGiamTotNhat = giaGiamHienTai;
                            }
                        }

                        // Set giá giảm tốt nhất vào hóa đơn chi tiết
                        hoaDonChiTiet.setGiaGiam(giaGiamTotNhat);

                        // Lưu hóa đơn chi tiết
                        hoaDonChiTietRepository.save(hoaDonChiTiet);
                    }
                }
            }
            return ResponseEntity.ok("Chi tiết hóa đơn đã được tạo thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra khi tạo chi tiết hóa đơn");
        }
    }




    @PutMapping(Admin.SELL_UPDATE)
    public ResponseEntity<?> update(@RequestBody BanHangRequest request ) {
        HoaDon hoaDon = hoaDonRepository.findById(request.getId()).orElse(null);
        if(hoaDon == null) {
            return ResponseEntity.notFound().build();
        }
        hoaDon.setIdPhieuGiamGia(request.getIdPhieuGiamGia());
        hoaDon.setIdNguoiDung(request.getIdNguoiDung());
        hoaDon.setNguoiTao(request.getNguoiTao());
        hoaDon.setIdDotGiamGia(request.getIdDotGiamGia());
        hoaDon.setSoDienThoai(request.getSoDienThoai());
        hoaDon.setDiaChi(request.getDiaChi());
        hoaDon.setGiaGoc(request.getGiaGoc());
        hoaDon.setGiaGiam(request.getGiaGiam());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setLoaiHoaDon(request.getLoaiHoaDon());
        hoaDon.setGhiChu(request.getGhiChu());
        hoaDon.setQrCode(request.getQrCode());
        hoaDon.setTienVanChuyen(request.getTienVanChuyen());
        hoaDon.setTrangThai(request.getTrangThai());

        return ResponseEntity.ok(hoaDonRepository.save(hoaDon));


    }


    @PutMapping(Admin.SELL_SET_DELETE)

    public ResponseEntity<?> delete(@PathVariable UUID id) {
        HoaDon hd = hoaDonRepository.findById(id).orElse(null);
        if(hd != null) {
            hoaDonRepository.setDeleted(!hd.getDeleted(),id);
            return ResponseEntity.ok("ok"+id);
        }
        return ResponseEntity.notFound().build();
    }



    @PutMapping(Admin.SELL_ORDER_PRODUCT_COMPLETE)
    public ResponseEntity<?> updateProductQuantity(@PathVariable UUID id, @RequestBody QuantitySoldRequest quantitySoldRequest) {
        SanPhamChiTiet spct = sanPhamChiTietRepository.findById(id).orElse(null);
        if (spct == null) {
            throw new RuntimeException("Sản phẩm không tồn tại");
        }
        int quantitySold = quantitySoldRequest.getQuantitySold();
        if (spct.getSoLuong() < quantitySold) {
            throw new RuntimeException("Không đủ số lượng sản phẩm trong kho");
        }
        spct.setSoLuong(spct.getSoLuong() - quantitySold);
        sanPhamChiTietRepository.save(spct);
        return ResponseEntity.ok(spct);
    }

    @PutMapping(Admin.SELL_ORDER_VOUCHER_COMPLETE)
    public ResponseEntity<?> updateVoucherQuantity(@PathVariable UUID id, @RequestBody QuantitySoldVoucherRequest request) {
        PhieuGiamGia pgg = phieuGiamGiaRepository.findById(id).orElse(null);
        if (pgg == null) {
            throw new RuntimeException("pgg không tồn tại");
        }
        int SLpgg = request.getQuantitySoldVoucher();
        if(pgg.getSoLuong() < SLpgg) {
            throw new RuntimeException("het phieu");
        }
        int soLuongPGGconlai = pgg.getSoLuong()-SLpgg;
        if(soLuongPGGconlai == 0) {
            pgg.setTrangThai("Hết Số Lượng");
        }
        pgg.setSoLuong(soLuongPGGconlai);
        phieuGiamGiaRepository.save(pgg);
        return ResponseEntity.ok(pgg);
    }


    @PostMapping(Admin.SELL_QR)
    public ResponseEntity<String> generateQrCode(@RequestBody QrRequest qrRequest) {
        // Gọi service để tạo QR code
        String qrResponse = vietQrService.generateQrCode(
                qrRequest.getAccountNo(),
                qrRequest.getAccountName(),
                qrRequest.getAcqId(),
                qrRequest.getAddInfo(),
                qrRequest.getAmount()
        );
        return ResponseEntity.ok(qrResponse);
    }


}
