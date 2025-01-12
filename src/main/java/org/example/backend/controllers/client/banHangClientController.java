package org.example.backend.controllers.client;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestAdd;
import org.example.backend.dto.response.banHang.banHangClient;
import org.example.backend.dto.response.banHang.banHangClientResponse;
import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
import org.example.backend.dto.response.quanLyDonHang.hoaDonClientResponse;
import org.example.backend.models.GioHang;
import org.example.backend.models.GioHangChiTiet;
import org.example.backend.models.HoaDon;
import org.example.backend.models.LichSuHoaDon;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.models.ThanhToan;
import org.example.backend.repositories.GioHangChiTietRepository;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.services.GioHangChiTietService;
import org.example.backend.services.GioHangService;
import org.example.backend.services.HoaDonChiTietService;
import org.example.backend.services.LichSuHoaDonService;
import org.example.backend.services.SanPhamChiTietService;
import org.example.backend.services.ThanhToanService;
import org.example.backend.services.TraHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class banHangClientController {

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    GioHangChiTietService gioHangChiTietService;
    @Autowired
    GioHangService gioHangService;
//    @Autowired
//    GioHangChiTietService gioHangChiTietService;
    @Autowired
    private TraHangService traHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private LichSuHoaDonService lichSuHoaDonService;
    @Autowired
    private ThanhToanService thanhToanService;
    @Autowired
    private HoaDonRepository hoaDonRepository;


//    @GetMapping(Admin.SELL_CLIENT_GET_ALL)
//    public ResponseEntity<?> getbanHangClient(@RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
//                                              @RequestParam(value = "page", defaultValue = "0") int page
//    ){
//        PageResponse<List<banHangClientResponse>> bhPage = sanPhamChiTietService.getbanHangClient(page, itemsPerPage);
//        ResponseData<PageResponse<List<banHangClientResponse>>> responseData = ResponseData.<PageResponse<List<banHangClientResponse>>>builder()
//                .message("Get all banHangCient done")
//                .status(HttpStatus.OK.value())
//                .data(bhPage)
//                .build();
//        return ResponseEntity.ok(responseData);
//    }

    @GetMapping(Admin.SELL_CLIENT_GET_BY_ID_DGG)
    public ResponseEntity<?> getbanHangClientbyIDDGG(@RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @PathVariable UUID id
    ){
        PageResponse<List<banHangClientResponse>> bhPage = sanPhamChiTietService.getbanHangClientbyIDDGG(page,itemsPerPage,id);
        ResponseData<PageResponse<List<banHangClientResponse>>> responseData = ResponseData.<PageResponse<List<banHangClientResponse>>>builder()
                .message("Get all banHangCient done")
                .status(HttpStatus.OK.value())
                .data(bhPage)
                .build();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(Admin.CART_GET_BY_ID)
    public ResponseEntity<?> finbyIDKH(@PathVariable UUID id
    ){
        return ResponseEntity.ok().body(gioHangService.finbyIDGioHang(id));
    }

    @GetMapping(Admin.CART_DETAIL_GET_ALL)
    public ResponseEntity<?> getALLCart(){
        return ResponseEntity.ok().body(gioHangChiTietService.findAll());
    }

    @PostMapping(CART_CREATE)
    public ResponseEntity<?> createCart(@RequestBody GioHangChiTiet ghAdd) {
//        GioHangChiTiet gh = new GioHangChiTiet();
        return ResponseEntity.ok().body(gioHangChiTietService.save(ghAdd));
    }

    @PostMapping(CART_UPDATE)
    public ResponseEntity<?> updateCart(@PathVariable UUID id,@RequestBody GioHangChiTiet ghUpdate) {
        ghUpdate.setId(id);
        return ResponseEntity.ok().body(gioHangChiTietService.save(ghUpdate));
    }

    @DeleteMapping(CART_DELETE)
    public ResponseEntity<?> deleteCart(@RequestParam List<UUID> listId) {
        for (UUID id : listId) {
            gioHangChiTietService.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    //tim kiem spct client

    @GetMapping(SELL_CLIENT_SEARCH_1)
    public ResponseEntity<?> getBanHangClient(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "tenSp", required = false) String tenSp,
            @RequestParam(value = "tenKichThuoc", required = false) String tenKichThuoc,
            @RequestParam(value = "tenMauSac", required = false) String tenMauSac,
            @RequestParam(value = "tenDanhMuc", required = false) String tenDanhMuc,
            @RequestParam(value = "tenHang", required = false) String tenHang,
            @RequestParam(value = "giaMin", required = false) BigDecimal giaMin,
            @RequestParam(value = "giaMax", required = false) BigDecimal giaMax
    ) {
        // Gọi service để lấy dữ liệu
        PageResponse<List<banHangClientResponse>> bhPage = sanPhamChiTietService.searchBanHangClient(
                page, itemsPerPage, tenSp, tenKichThuoc, tenMauSac,tenDanhMuc,tenHang,giaMin, giaMax
        );

        // Tạo ResponseData
        ResponseData<PageResponse<List<banHangClientResponse>>> responseData = ResponseData.<PageResponse<List<banHangClientResponse>>>builder()
                .message("Get all banHangClient done")
                .status(HttpStatus.OK.value())
                .data(bhPage)
                .build();

        return ResponseEntity.ok(responseData);
    }

    //lay 5 san pham moi nhat

    @GetMapping(Admin.SELL_CLIENT_TOP)
    public ResponseEntity<List<banHangClientResponse>> getTop5SanPhamMoiNhat() {
        List<banHangClientResponse> top5SanPham = sanPhamChiTietService.getTop5SanPhamMoiNhat();
        return ResponseEntity.ok(top5SanPham); // Trả về danh sách dưới dạng JSON
    }

    // hien cac sp da ap dung dot giam gia
    @GetMapping(Admin.SELL_CLIENT_SALE_SP)
    public ResponseEntity<List<banHangClientResponse>> getSanPhamGiamGia(
            @RequestParam List<String> trangThais,
            @RequestParam UUID id
    ) {
        List<banHangClientResponse> sanPhamGiamGia = sanPhamChiTietService.getSanPhamGiamGia(trangThais, id);
        return ResponseEntity.ok(sanPhamGiamGia);
    }

       // API để lấy hóa đơn theo ID khách hàng
//    @GetMapping(PRODUCT_RETURN_GET_BY_IDKH)
//    public ResponseEntity<List<hoaDonClientResponse>> getHoaDonByIdNguoiDung(@PathVariable UUID idNguoiDung) {
//        List<hoaDonClientResponse> hoaDonChiTietList = traHangService.getHoaDonByIdNguoiDung(idNguoiDung);
//        if (hoaDonChiTietList.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(hoaDonChiTietList);
//    }

    
//    @GetMapping(PRODUCT_RETURN_DETAIL_BY_ID)
//    public ResponseEntity<hoaDonClientResponse> getHoaDonCtById(
//            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @PathVariable UUID id
//    ) {
//        hoaDonClientResponse hoaDonChiTiet = traHangService.getHoaDonById(id);
//        if (hoaDonChiTiet != null) {
//            return ResponseEntity.ok().body(hoaDonChiTiet);
//        }
//        return ResponseEntity.notFound().build();
//    }

    // set phuong thuc thanh toan bên client khi thanh toan
    @PostMapping(SELL_CLIENT_SET_PHUONG_THUC_THANH_TOAN)
    public ResponseEntity<ThanhToan> setPhuongThucThanhToan(
            @RequestParam UUID idHoaDon,
            @RequestParam String phuongThucThanhToan
    ) {
        ThanhToan thanhToan = thanhToanService.createThanhToan(idHoaDon, phuongThucThanhToan);
        return ResponseEntity.ok(thanhToan);
    }

    // set lich su thanh toan bên client khi thanh toan
    @PostMapping(SELL_CLIENT_SET_LICH_SU_HOA_DON)
    public ResponseEntity<?> setLichSuHoaDon(
            @RequestParam UUID idHoaDon,
            @RequestParam String trangThai,
            @RequestParam String moTa
    ) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).orElse(null);
        if (hoaDon == null) {
            return ResponseEntity.notFound().build();
        }
        if (trangThai.equals("Đã Hủy")){
            String cxn = "Chờ Xác Nhận";
            String cbh = "Chuẩn Bị Hàng";
            if (!hoaDon.getTrangThai().equals(cxn) && !hoaDon.getTrangThai().equals(cbh)) {
                return ResponseEntity.badRequest().body("Đơn hàng không thể hủy vui lòng kiểm tra lại");
            }
        }

// Cập nhật trạng thái thành "Đã Hủy" nếu điều kiện trên được thỏa mãn
        hoaDon.setTrangThai(trangThai);
        hoaDonRepository.save(hoaDon);
        LichSuHoaDon lichSuHoaDon = lichSuHoaDonService.createLichSuHoaDon(idHoaDon, trangThai, moTa);
        return ResponseEntity.ok(lichSuHoaDon);
    }

    // set lich su thanh toan bên client khi thanh toan
    @GetMapping(SELL_CLIENT_GET_LICH_SU_HOA_DON)
    public ResponseEntity<List<LichSuHoaDon>> getLichSuHoaDon(
            @PathVariable UUID id
    ) {
        List<LichSuHoaDon> lichSuHoaDon = lichSuHoaDonService.getLichSuHoaDonById(id);
        if (lichSuHoaDon.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lichSuHoaDon);
    }

    @GetMapping(SELL_CLIENT_GET_PHUONG_THUC_THANH_TOAN)
    public ResponseEntity<ThanhToan> getPhuongThucThanhToan(
            @PathVariable UUID id
    ) {
        ThanhToan thanhToan = thanhToanService.getPhuongThucThanhToanByIdHoaDon(id);
        if (thanhToan == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(thanhToan);
    }

    @PutMapping(Admin.SELL_CLIENT_HOAN_LAI_SO_LUONG_SP)
    public ResponseEntity<SanPhamChiTiet> hoanLaiSoLuongSP(
        @RequestParam UUID idSPCT,
        @RequestParam Integer soLuong
    ) {
        Optional<SanPhamChiTiet> sanPhamChiTiet = sanPhamChiTietRepository.findById(idSPCT);
        if (sanPhamChiTiet.isPresent()) {
            SanPhamChiTiet spct = sanPhamChiTiet.get();
            spct.setSoLuong(spct.getSoLuong() + soLuong);
            sanPhamChiTietRepository.save(spct);
            return ResponseEntity.ok(spct);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.SELL_CLIENT_GET_BY_ID_SPCT)
    public ResponseEntity<?> getbanHangClientbyIDSPCT(@RequestParam List<UUID> ids) {
        List<banHangClient> resultList = new ArrayList<>();
        String hoatDong = "Hoạt động";
        for (UUID id : ids) {
            banHangClient products = sanPhamChiTietService.getbanHangClientbyIDSPCT(id);
// s.deleted = false
            if(products.getTrangThai().equals(hoatDong)&&products.getSoLuong()>0&&
                    products.getTrangThaiSanPham().equals(hoatDong)&&products.getDeleted()==false) {
                resultList.add(products);
            }
        }
        
        return ResponseEntity.ok(resultList);
    }

}
