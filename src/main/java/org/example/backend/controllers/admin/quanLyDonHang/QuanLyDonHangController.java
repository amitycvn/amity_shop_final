package org.example.backend.controllers.admin.quanLyDonHang;


import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.HoaDon;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.services.HoaDonChiTietService;
import org.example.backend.services.HoaDonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.BILL_EXCEL;
import static org.example.backend.constants.api.Admin.BILL_GET_ALL;
import static org.example.backend.constants.api.Admin.BILL_GET_BY_ID;
import static org.example.backend.constants.api.Admin.COUT_BY_STATUS_BILL;

@RestController
public class QuanLyDonHangController {

    final HoaDonChiTietRepository hoaDonChiTietRepository;
    private final HoaDonChiTietService hoaDonChiTietService;
    private final HoaDonService hoaDonService;
    private final HoaDonRepository hoaDonRepository;

    public QuanLyDonHangController(HoaDonChiTietRepository hoaDonChiTietRepository,
                                   HoaDonChiTietService hoaDonChiTietService, HoaDonRepository hoaDonRepository, HoaDonService hoaDonService) {
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.hoaDonChiTietService = hoaDonChiTietService;
        this.hoaDonRepository = hoaDonRepository;
        this.hoaDonService = hoaDonService;
    }
    @GetMapping(BILL_GET_BY_ID)
    public ResponseEntity<?> getHoaDonById(@PathVariable UUID id) {
        HoaDon HoaDon = hoaDonRepository.findById(id).orElse(null);
        if (HoaDon != null) {
            return ResponseEntity.ok().body(HoaDon);
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping(BILL_GET_ALL)
//    public ResponseEntity<ResponseData<PageResponse<List<QuanLyDonHangRespose>>>> getAllBillPage(
//            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "status", required = false, defaultValue = "") String status
//    ) {
//        PageResponse<List<QuanLyDonHangRespose>> billPage = hoaDonService.getHoaDonByStats(page, itemsPerPage, status);
//        ResponseData<PageResponse<List<QuanLyDonHangRespose>>> responseData = ResponseData.<PageResponse<List<QuanLyDonHangRespose>>>builder()
//                .message("Get paginated users done")
//                .status(HttpStatus.OK.value())
//                .data(billPage)
//                .build();
//        return ResponseEntity.ok(responseData);
//    }

    @GetMapping(BILL_GET_ALL)
    public ResponseEntity<ResponseData<PageResponse<List<QuanLyDonHangRespose>>>> getAllBillPage(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "status", required = false, defaultValue = "") String status,
            @RequestParam(required = false, defaultValue = "") String keyFind,
            @RequestParam(required = false, defaultValue = "") String loai,
            @RequestParam(required = false) Instant minNgay,
            @RequestParam(required = false) Instant maxNgay,
            @RequestParam(required = false) BigDecimal minGia,
            @RequestParam(required = false) BigDecimal maxGia
            ) {
        PageResponse<List<QuanLyDonHangRespose>> billPage = hoaDonService.searchHoaDon(page, itemsPerPage,keyFind,loai,minNgay,maxNgay,minGia,maxGia,status);
        ResponseData<PageResponse<List<QuanLyDonHangRespose>>> responseData = ResponseData.<PageResponse<List<QuanLyDonHangRespose>>>builder()
                .message("Get paginated users done")
                .status(HttpStatus.OK.value())
                .data(billPage)
                .build();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(COUT_BY_STATUS_BILL)
    public ResponseEntity<List<Long>> countHoaDonByStatuses(@RequestParam List<String> statuses) {
        // Gọi service để xử lý
        List<Long> statusCounts = hoaDonService.countHoaDonByStatuses(statuses);
        return ResponseEntity.ok(statusCounts);
    }

    @GetMapping(BILL_EXCEL)
    public ResponseEntity<byte[]> exportInvoiceExcel() {
        try {
            // Lấy danh sách hóa đơn từ service
            List<QuanLyDonHangRespose> invoiceList = hoaDonService.getHDGetAll();

            // Tạo file Excel từ danh sách hóa đơn
            byte[] excelData = hoaDonService.exportInvoiceToExcel(invoiceList);

            // Trả về response với file Excel
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=hoaDon.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
