package org.example.backend.controllers.admin.phieuGiamGia;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.Constant;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestAdd;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestUpdate;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponseAdmin;
import org.example.backend.mapper.phieuGiamGia.phieuGiamGiaMapper;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.models.PhieuGiamGiaNguoiDung;
import org.example.backend.services.KhachHangService;
import org.example.backend.services.PhieuGiamGiaService;
import org.example.backend.services.PhieuGiamGiaNguoiDungService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.VOUCHER_CREATE;
import static org.example.backend.constants.api.Admin.VOUCHER_DELETE;
import static org.example.backend.constants.api.Admin.VOUCHER_EXCEL;
import static org.example.backend.constants.api.Admin.VOUCHER_GET_BY_ID;
import static org.example.backend.constants.api.Admin.VOUCHER_GET_BY_ID_KH;
import static org.example.backend.constants.api.Admin.VOUCHER_SEARCH;
import static org.example.backend.constants.api.Admin.VOUCHER_UPDATE;
import static org.example.backend.constants.api.Admin.VOUCHER_UPDATE_STATUS;

@RestController
public class PhieuGiamGiaController {

    // @Autowired
    final PhieuGiamGiaService PGGService;
    final KhachHangService khachHangService;
    final PhieuGiamGiaNguoiDungService phieuGiamGiaNguoiDungService;

    final phieuGiamGiaMapper PGGMapper;

    public PhieuGiamGiaController(PhieuGiamGiaService PGGService, phieuGiamGiaMapper PGGMapper,
            KhachHangService khachHangService, PhieuGiamGiaNguoiDungService phieuGiamGiaNguoiDungService) {
        this.PGGService = PGGService;
        this.PGGMapper = PGGMapper;
        this.khachHangService = khachHangService;
        this.phieuGiamGiaNguoiDungService = phieuGiamGiaNguoiDungService;
    }

    @GetMapping(Admin.VOUCHER_GET_ALL)
    public ResponseEntity<?> getALlVoucher(@RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "") String keyFind,
            @RequestParam(required = false, defaultValue = "") String trangThai,
            @RequestParam(required = false) Instant minNgay,
            @RequestParam(required = false) Instant maxNgay,
            @RequestParam(required = false) BigDecimal minGia,
            @RequestParam(required = false) BigDecimal maxGia,
            // @RequestParam(value = "loai",required = false, defaultValue = "0") Integer
            // loai,
            @RequestParam(required = false, defaultValue = "1") Integer sapXep) {
        PageResponse<List<phieuGiamGiaReponse>> PGGPage = PGGService.searchPGG(page, itemsPerPage, keyFind, trangThai,
                sapXep, minNgay, maxNgay, minGia, maxGia);
        ResponseData<PageResponse<List<phieuGiamGiaReponse>>> responseData = ResponseData
                .<PageResponse<List<phieuGiamGiaReponse>>>builder()
                .message("Get all voucher done")
                .status(HttpStatus.OK.value())
                .data(PGGPage)
                .build();

        return ResponseEntity.ok(responseData);
    }
    //lay pgg ban hang tai quay
    @GetMapping(Admin.VOUCHER_GET_ALL_ADMIN)
    public ResponseEntity<?> getALlVoucherAdmin(
            @RequestParam(required = false, defaultValue = "") String keyFind
    ) {
        List<phieuGiamGiaReponseAdmin> PGGPage = PGGService.getAllPGGAdmin(keyFind);


        return ResponseEntity.ok(PGGPage);
    }

    @PostMapping(VOUCHER_CREATE)
    public ResponseEntity<?> createVoucher(@RequestBody phieuGiamGiaRequestAdd PGGadd) {
        PhieuGiamGia pgg = new PhieuGiamGia();

        // Xác định trạng thái dựa trên thời gian
        Instant now = Instant.now();
        String trangThai;

        if (now.isBefore(PGGadd.getNgayBatDau())) {
            trangThai = "Sắp diễn ra";
        } else if (now.isAfter(PGGadd.getNgayBatDau()) && now.isBefore(PGGadd.getNgayKetThuc())) {
            trangThai = "Đang diễn ra";
        } else {
            trangThai = "Đã kết thúc";
        }

        // Set trạng thái vào request trước khi mapping
        PGGadd.setTrangThai(trangThai);

        // Map các thuộc tính từ request vào entity
        PGGMapper.createPhieuGiamGiaFromDto(PGGadd, pgg);

        return ResponseEntity.ok().body(PGGService.save(pgg));
    }

    @PutMapping(VOUCHER_UPDATE)
    public ResponseEntity<?> updateVoucher(
            @PathVariable UUID id,
            @RequestBody phieuGiamGiaRequestUpdate PGGupdate) {
        Optional<PhieuGiamGia> existingPGG = PGGService.findById(id);
        // Xác định trạng thái dựa trên thời gian
        Instant now = Instant.now();
        String trangThai;

        if (now.isBefore(PGGupdate.getNgayBatDau())) {
            trangThai = "Sắp diễn ra";
        } else if (now.isAfter(PGGupdate.getNgayBatDau()) && now.isBefore(PGGupdate.getNgayKetThuc())) {
            trangThai = "Đang diễn ra";
        } else {
            trangThai = "Đã kết thúc";
        }
        if (existingPGG.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PhieuGiamGia pgg = existingPGG.get();
        PGGMapper.updatePhieuGiamGiaFromDto(PGGupdate, pgg);
        pgg.setTrangThai(trangThai);
        return ResponseEntity.ok(PGGService.save(pgg));
    }

    // @DeleteMapping(VOUCHER_DELETE)
    // public ResponseEntity<?> deleteVoucher(@PathVariable UUID id) {
    // Optional<PhieuGiamGia> pgg = PGGService.findById(id);
    // if (pgg.isPresent()) {
    // PGGService.deleteById(id);
    // return ResponseEntity.ok().body("Delete id: " + id);
    // }
    // return ResponseEntity.notFound().build();
    // }

    @GetMapping(VOUCHER_GET_BY_ID)
    public ResponseEntity<?> getVoucherById(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            phieuGiamGiaReponse pggReponse = new phieuGiamGiaReponse();
            PGGMapper.getDtoFromPhieuGiamGia(pggReponse, pgg);
            return ResponseEntity.ok().body(pggReponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(VOUCHER_GET_BY_ID_KH)
    public ResponseEntity<List<phieuGiamGiaReponse>> getVoucherByIdKH(@PathVariable UUID id) {
        List<phieuGiamGiaReponse> pgg = PGGService.getPGGGetAllbyIDKH(id);
        return ResponseEntity.ok(pgg);
    }

    @GetMapping(VOUCHER_DELETE)
    public ResponseEntity<?> setVoucherDelete(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            PGGService.setDeletedPhieuGiamGia(!pgg.getDeleted(), id);
            return ResponseEntity.ok().body("Set deleted id: " + id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(VOUCHER_UPDATE_STATUS)
    public ResponseEntity<?> setVoucherUpdateStatus(@PathVariable UUID id) {
        PhieuGiamGia pgg = PGGService.findById(id).orElse(null);
        if (pgg != null) {
            String currentStatus = pgg.getTrangThai();
            Instant now = Instant.now();

            if ("Sắp diễn ra".equals(currentStatus)) {
                PGGService.updateTrangThaiAndNgayKetThuc("Đã hủy", pgg.getNgayKetThuc(), id);
                return ResponseEntity.ok().body("Voucher id: " + id + " đã được hủy.");
            } else if ("Đang diễn ra".equals(currentStatus)) {
                PGGService.updateTrangThaiAndNgayKetThuc("Đã kết thúc", now, id);
                return ResponseEntity.ok().body("Voucher id: " + id + " đã kết thúc.");
            } else {
                return ResponseEntity.badRequest().body("Không thể thay đổi trạng thái của voucher id: " + id);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // @GetMapping(VOUCHER_SEARCH)
    // public ResponseEntity<?> SearchPGG(@PathVariable String find,
    // @PathVariable String filterType
    // ){
    // String filter = "";
    // if(filterType.equals("tienMat")) filter = "and p.loai = true ";
    // if(filterType.equals("phanTram")) filter = "and p.loai = false";
    // if(filterType.equals("dangHoatDong")) filter = "and p.trangThai like \"%Hoạt
    // Động%\"";
    // if(filterType.equals("ngungHoatDong")) filter = "and p.trangThai like
    // \"%ngừng hoạt động%\"";
    // List<phieuGiamGiaReponse> result = PGGService.searchPGG("%"+find+"%" ,
    // filterType);
    // if(result.isEmpty()){
    // return ResponseEntity.noContent().build();
    // }
    // return ResponseEntity.ok(result);
    // }

    @GetMapping(VOUCHER_EXCEL)
    public ResponseEntity<byte[]> exportExcel() {
        try {
            List<phieuGiamGiaReponse> voucherList = PGGService.getPGGGetAll(); // Lấy dữ liệu phiếu giảm giá
            byte[] excelData = PGGService.exportToExcel(voucherList);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=phieuGiamGia.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(Admin.VOUCHER_CREATE_PGG_KH)
    public ResponseEntity<?> createPhieuGiamGiaNguoiDung(
            @RequestParam UUID idKhachHang,
            @RequestParam UUID idPhieuGiamGia) {
        try {
            // Kiểm tra xem khách hàng và phiếu giảm giá có tồn tại không
            if (!khachHangService.existsById(idKhachHang)) {
                return ResponseEntity.badRequest().body("Khách hàng không tồn tại");
            }

            if (!PGGService.existsById(idPhieuGiamGia)) {
                return ResponseEntity.badRequest().body("Phiếu giảm giá không tồn tại");
            }

            // Tạo phiếu giảm giá người dùng mới
            PhieuGiamGiaNguoiDung phieuGiamGiaNguoiDung = new PhieuGiamGiaNguoiDung();
            phieuGiamGiaNguoiDung.setIdNguoiDung(khachHangService.findById(idKhachHang).get());
            phieuGiamGiaNguoiDung.setIdPhieuGiamGia(PGGService.findById(idPhieuGiamGia).get());
            phieuGiamGiaNguoiDung.setTrangThai("Chưa sử dụng");
            phieuGiamGiaNguoiDung.setDeleted(false);
            // Lưu vào database
            phieuGiamGiaNguoiDungService.save(phieuGiamGiaNguoiDung);

            return ResponseEntity.ok("Tạo phiếu giảm giá người dùng thành công");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi tạo phiếu giảm giá người dùng");
        }
    }

    @GetMapping(Admin.VOUCHER_SET_DA_SU_DUNG)
    public ResponseEntity<?> setDaSuDung(
        @RequestParam UUID idKhachHang,
            @RequestParam UUID idPhieuGiamGia) {
        PhieuGiamGiaNguoiDung pgg = phieuGiamGiaNguoiDungService.findByIdNguoiDungIdAndIdPhieuGiamGiaId(idKhachHang,
                idPhieuGiamGia);
        return ResponseEntity.ok(pgg);
    }

    //check số lương phiêu giam giá

    @GetMapping("/api/v1/admin/kiem-tra-so-luong-giam-gia/{id}")
    public ResponseEntity<Map<String, Object>> checkVoucherQuantity(
            @PathVariable("id") UUID saleId,
            @RequestParam("soLuong") int requiredQuantity) {
        boolean isAvailable = PGGService.checkVoucherQuantity(saleId, requiredQuantity);
        String message = isAvailable
                ? "Số lượng phiếu giảm giá đủ"
                : "Không đủ số lượng phiếu giảm giá";
        return ResponseEntity.ok(Map.of(
                "isAvailable", isAvailable,
                "message", message
        ));
    }
}
