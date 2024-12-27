package org.example.backend.services;

import org.example.backend.models.PhieuGiamGiaNguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.repositories.PhieuGiamGiaNguoiDungRepository;
import org.example.backend.repositories.PhieuGiamGiaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PhieuGiamGiaNguoiDungService extends GenericServiceImpl<PhieuGiamGiaNguoiDung, UUID> {
    private final PhieuGiamGiaNguoiDungRepository phieuGiamGiaNguoiDungRepository;
    private final NguoiDungRepository khachHangRepository;
    private final PhieuGiamGiaRepository phieuGiamGiaRepository;

    public PhieuGiamGiaNguoiDungService(PhieuGiamGiaNguoiDungRepository repository, NguoiDungRepository khachHangRepository, PhieuGiamGiaRepository phieuGiamGiaRepository) {
        super(repository);
        this.phieuGiamGiaNguoiDungRepository = repository;
        this.khachHangRepository = khachHangRepository;
        this.phieuGiamGiaRepository = phieuGiamGiaRepository;
    }

    public PhieuGiamGiaNguoiDung findByIdNguoiDungIdAndIdPhieuGiamGiaId(UUID idNguoiDung, UUID idPhieuGiamGia) {
        PhieuGiamGiaNguoiDung phieuGiamGiaNguoiDung = phieuGiamGiaNguoiDungRepository.findByIdNguoiDungIdAndIdPhieuGiamGiaId(idNguoiDung, idPhieuGiamGia);
        
        if (phieuGiamGiaNguoiDung != null) {
            phieuGiamGiaNguoiDung.setTrangThai("Đã sử dụng");
            return phieuGiamGiaNguoiDungRepository.save(phieuGiamGiaNguoiDung);
        } else {
            PhieuGiamGiaNguoiDung newPhieu = new PhieuGiamGiaNguoiDung();
            newPhieu.setIdNguoiDung(khachHangRepository.findById(idNguoiDung).get());
            newPhieu.setIdPhieuGiamGia(phieuGiamGiaRepository.findById(idPhieuGiamGia).get());
            newPhieu.setTrangThai("Đã sử dụng");
            newPhieu.setDeleted(false);
            return phieuGiamGiaNguoiDungRepository.save(newPhieu);
        }
    }
}
