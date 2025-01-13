package org.example.backend.services;

import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
import org.example.backend.dto.response.quanLyDonHang.hoaDonClientResponse;
import org.example.backend.dto.response.traHang.TraHangResponse;
import org.example.backend.models.HoaDon;
import org.example.backend.models.TraHang;
import org.example.backend.repositories.HoaDonChiTietRepository;
import org.example.backend.repositories.HoaDonRepository;
import org.example.backend.repositories.TraHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class TraHangService extends GenericServiceImpl<TraHang, UUID> {
   private final TraHangRepository traHangRepository;
   private final HoaDonChiTietRepository hoaDonChiTietRepository;
   private final HoaDonRepository hoaDonRepository;
    public TraHangService(JpaRepository<TraHang, UUID> repository, TraHangRepository traHangRepository,HoaDonChiTietRepository hoaDonChiTietRepository,HoaDonRepository hoaDonRepository) {
        super(repository);
        this.traHangRepository = traHangRepository;
        this.hoaDonChiTietRepository = hoaDonChiTietRepository;
        this.hoaDonRepository = hoaDonRepository;
    }


//    public List<hoaDonClientResponse> getHoaDonByIdNguoiDung(UUID idNguoiDung) {
//        List<QuanLyDonHangRespose> hoaDon = traHangRepository.getHoaDonByIdKh(idNguoiDung);
//        List<hoaDonClientResponse> hoaDonClientResponse = new ArrayList<>();
//        for (QuanLyDonHangRespose hd : hoaDon) {
//        hoaDonClientResponse hoaDonClient = new hoaDonClientResponse();
//        hoaDonClient.setId(hd.getId());
//        hoaDonClient.setMaHD(hd.getMaHD());
//        hoaDonClient.setTenKhachHang(hd.getTenKhachHang());
//        hoaDonClient.setSoDienThoai(hd.getSoDienThoai());
//        hoaDonClient.setDiaChi(hd.getDiaChi());
//        hoaDonClient.setTongTien(hd.getTongTien());
//        hoaDonClient.setLoaiHoaDon(hd.getLoaiHoaDon());
//        hoaDonClient.setNgayTao(hd.getNgayTao());
//        hoaDonClient.setTrangThai(hd.getTrangThai());
//        hoaDonClient.setDeleted(hd.getDeleted());
//        hoaDonClient.setHoaDonChiTiets(hoaDonChiTietRepository.getByPageHoaDonChiTiet(PageRequest.of(0, 5),hd.getId()).getContent());
//        hoaDonClientResponse.add(hoaDonClient);
//        }
//        return hoaDonClientResponse;
//    }
//
//    public hoaDonClientResponse getHoaDonById(UUID id) {
//        HoaDon hd = hoaDonRepository.findById(id).orElse(null);
//        hoaDonClientResponse hoaDonClient = new hoaDonClientResponse();
//        hoaDonClient.setId(hd.getId());
//        hoaDonClient.setMaHD(hd.getMa());
//        hoaDonClient.setTenKhachHang(hd.getIdNguoiDung().getTen());
//        hoaDonClient.setSoDienThoai(hd.getSoDienThoai());
//        hoaDonClient.setDiaChi(hd.getDiaChi());
//        hoaDonClient.setGiaGoc(hd.getGiaGoc());
//        hoaDonClient.setGiaGiam(hd.getGiaGiam());
//        hoaDonClient.setTongTien(hd.getTongTien());
//        hoaDonClient.setGhiChu(hd.getGhiChu());
//        hoaDonClient.setNgayDuKienNhan(hd.getNgayDuKienNhan());
//        hoaDonClient.setTienVanChuyen(hd.getTienVanChuyen());
//        hoaDonClient.setLoaiHoaDon(hd.getLoaiHoaDon());
//        hoaDonClient.setNgayTao(hd.getNgayTao());
//        hoaDonClient.setTrangThai(hd.getTrangThai());
//        hoaDonClient.setDeleted(hd.getDeleted());
//        hoaDonClient.setHoaDonChiTiets(hoaDonChiTietRepository.getByPageHoaDonChiTiet(PageRequest.of(0, 5),hd.getId()).getContent());
//        return hoaDonClient;
//    }
//
//
//    public List<hoaDonChiTietReponse> getHoaDonCtById(UUID id) {
//        return traHangRepository.getHoaDonCtByID(id);
//    }

}
