package org.example.backend.services;

import org.example.backend.models.HoaDon;
import org.example.backend.models.LichSuHoaDon;
import org.example.backend.models.ThanhToan;
import org.example.backend.repositories.HoaDonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ThanhToanService extends GenericServiceImpl<ThanhToan , UUID> {
    public ThanhToanService(JpaRepository<ThanhToan, UUID> repository, HoaDonRepository hoaDonRepository) {
        super(repository);
        this.hoaDonRepository = hoaDonRepository;
    }

    private final HoaDonRepository hoaDonRepository;

    public ThanhToan createThanhToan(UUID idHoaDon , String phuongThucThanhToan) {
        ThanhToan thanhToan = new ThanhToan();
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).orElse(null);
        thanhToan.setIdHoaDon(hoaDon);
        thanhToan.setPhuongThuc(phuongThucThanhToan);
        thanhToan.setNgayTao(Instant.now());
        thanhToan.setDeleted(false);
        return repository.save(thanhToan);
    }

    public ThanhToan getPhuongThucThanhToanByIdHoaDon(UUID idHoaDon) {
        return repository.findAll().stream()
                .filter(tt -> tt.getIdHoaDon().getId().equals(idHoaDon))
                .findFirst()
                .orElse(null);
    }

}
