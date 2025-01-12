package org.example.backend.services;

import org.example.backend.dto.request.diaChi.DiaChiRequet;
import org.example.backend.models.DiaChi;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.DiaChiRepository;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class DiaChiService extends GenericServiceImpl<DiaChi, UUID>{

    @Autowired
    private DiaChiRepository diaChiRepository;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public DiaChiService(JpaRepository<DiaChi, UUID> repository) {
        super(repository);
    }

    // Lấy danh sách địa chỉ theo ID người dùng


    // Tạo mới địa chỉ
    public DiaChi createDiaChi(DiaChiRequet diaChiRequest) {
        DiaChi diaChi = new DiaChi();
        NguoiDung nd = nguoiDungRepository.findById(diaChiRequest.getIdNguoiDung()).orElseThrow(()-> new RuntimeException("người dùng không tôn tại"));
        diaChi.setIdNguoiDung(nd);
        diaChi.setTenNguoiNhan(diaChiRequest.getTenNguoiNhan());
        diaChi.setSoDienThoai(diaChiRequest.getSoDienThoai());
        diaChi.setIxa(diaChiRequest.getIxa());
        diaChi.setIhuyen(diaChiRequest.getIhuyen());
        diaChi.setIthanhPho(diaChiRequest.getIthanhPho());
        diaChi.setDiaChi(diaChiRequest.getDiaChi());
        diaChi.setNguoiTao("Hệ thống"); // Tạm thời để giá trị mặc định
        diaChi.setNgayTao(Instant.now());
        diaChi.setTrangThai("Hoạt động");

        return diaChiRepository.save(diaChi);
    }

    // Cập nhật địa chỉ
    public Optional<DiaChi> updateDiaChi(UUID id, DiaChiRequet diaChiRequest) {
        return diaChiRepository.findById(id).map(diaChi -> {
            diaChi.setTenNguoiNhan(diaChiRequest.getTenNguoiNhan());
            diaChi.setSoDienThoai(diaChiRequest.getSoDienThoai());
            diaChi.setIxa(diaChiRequest.getIxa());
            diaChi.setIhuyen(diaChiRequest.getIhuyen());
            diaChi.setIthanhPho(diaChiRequest.getIthanhPho());
            diaChi.setDiaChi(diaChiRequest.getDiaChi());
            diaChi.setNguoiSua("Hệ thống"); // Tạm thời để giá trị mặc định
            diaChi.setNgaySua(Instant.now());
            return diaChiRepository.save(diaChi);
        });
    }
}
