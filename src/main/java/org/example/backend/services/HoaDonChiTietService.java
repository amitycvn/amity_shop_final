package org.example.backend.services;

import org.example.backend.common.PageResponse;

import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.thongKe.ThongKeResponse;

import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;

import org.example.backend.dto.response.thongKe.Top5SanPhamResponse;
import org.example.backend.models.HoaDonChiTiet;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietService extends GenericServiceImpl<HoaDonChiTiet, UUID> {
    private final HoaDonChiTietRepository hoaDonChiTietRepository;
    public HoaDonChiTietService(JpaRepository<HoaDonChiTiet, UUID> repository ,HoaDonChiTietRepository hoaDonChiTietRepository) {
        super(repository);
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
    }


//    public List<ThongKeResponse> getThongKeData() {
//        return hoaDonChiTietRepository.getAllThongKe();

    public List<ThongKeResponse> getThongKeData(String trangThai, int year, int month, int day) {
        if (month == 0 && day == 0) {
            return hoaDonChiTietRepository.getAllThongKe(trangThai, year);
        }
        if (day == 0) {
            return hoaDonChiTietRepository.getAllThongKeByMonth(trangThai, year, month);
        }
        return hoaDonChiTietRepository.getAllThongKeByDay(trangThai, year, month, day);
    }
    public List<ThongKeResponse> getAllThongKeByDateRange(String trangThai,Instant startDate,Instant endDate) {
        return hoaDonChiTietRepository.getAllThongKeByDateRange(trangThai, startDate, endDate);
    }

    public List<ThongKeResponse> getThongKeDataMonth(String trangThai,int year, int month) {
        return hoaDonChiTietRepository.getAllThongKeByMonth(trangThai,year,month);
    }



    public PageResponse<List<hoaDonChiTietReponse>> getHoaDonChiTiet(int page, int size,UUID idHD) {
        Pageable pageable = PageRequest.of(page, size);
        Page<hoaDonChiTietReponse> hdctPage = hoaDonChiTietRepository.getByPageHoaDonChiTiet(pageable,idHD);
        return PageResponse.<List<hoaDonChiTietReponse>>builder()
                .page(hdctPage.getNumber())
                .size(hdctPage.getSize())
                .totalPage(hdctPage.getTotalPages())
                .items(hdctPage.getContent())
                .build();
    }
//    public Page<hoaDonChiTietReponse> getAllBillPage(Pageable pageable){
//        return hoaDonChiTietRepository.getByPageHoaDon(pageable);

//    }
    public List<hoaDonChiTietReponse> getHoaDonChiTietByMa(String maHD) {
        return hoaDonChiTietRepository.getByMaHoaDonChiTiet(maHD);
    }
    public List<Object[]>  getTop5SanPham(Pageable pageable) {
        return hoaDonChiTietRepository.top5SanPham();
    }

    public List<Object[]>  getSanPhamSapHet(Pageable pageable) {
        return hoaDonChiTietRepository.sanPhamSapHet();
    }


}
