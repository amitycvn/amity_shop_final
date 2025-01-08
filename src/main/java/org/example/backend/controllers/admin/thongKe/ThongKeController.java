package org.example.backend.controllers.admin.thongKe;

import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.thongKe.ThongKeResponse;

import org.example.backend.services.HoaDonChiTietService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@RestController
public class ThongKeController {
    private final HoaDonChiTietService hoaDonChiTietService;

    public ThongKeController(HoaDonChiTietService hoaDonChiTietService) {
        this.hoaDonChiTietService = hoaDonChiTietService;
    }


    @GetMapping(Admin.THONG_KE)
    public ResponseEntity<List<ThongKeResponse>> getThongKe(
            @RequestParam(defaultValue = "Đã thanh toán") String trangThai,
            @RequestParam(defaultValue = "0") int year,
            @RequestParam(defaultValue = "0") int month,
            @RequestParam(defaultValue = "0") int day
    ) {
        if (year <= 0) {
            year = LocalDate.now().getYear();
        }
        try {
            // Lấy dữ liệu thống kê
            List<ThongKeResponse> thongKeData = hoaDonChiTietService.getThongKeData(trangThai, year, month, day);
            return ResponseEntity.ok(thongKeData);
        } catch (Exception ex) {
            return null;
        }
    }
    @GetMapping(Admin.THONG_KE_PRO)
    public ResponseEntity<?> getThongKeByDateRange(
            @RequestParam String trangThai,
            @RequestParam Instant startDate,
            @RequestParam Instant endDate
    ) {
        List<ThongKeResponse> response = hoaDonChiTietService.getAllThongKeByDateRange(trangThai, startDate, endDate);
        return ResponseEntity.ok(response);
    }



    @GetMapping(Admin.THONG_KE_MONTH)
    public ResponseEntity<List<ThongKeResponse>> getThongKeMouth(
            @RequestParam(defaultValue = "Đã thanh toán") String trangThai,
            @RequestParam(defaultValue = "0") int year,
            @RequestParam(defaultValue = "0") int month
    ) {
        if (year == 0) {
            year = LocalDate.now().getYear();
        }
        List<ThongKeResponse> thongKeData = hoaDonChiTietService.getThongKeDataMonth(trangThai,year,month);
        return ResponseEntity.ok(thongKeData);
    }
    @GetMapping(Admin.TOP_5_SP)
    public List<Object[]>  getTop5SanPhamPaged(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hoaDonChiTietService.getTop5SanPham(pageable);
    }
    @GetMapping(Admin.SP_SAP_HET)
    public List<Object[]>  getSanPhamSapHet(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hoaDonChiTietService.getSanPhamSapHet(pageable);
    }



}
