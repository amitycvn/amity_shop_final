package org.example.backend.services;

import org.example.backend.models.HoaDon;
import org.example.backend.models.LichSuHoaDon;
import org.example.backend.repositories.HoaDonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LichSuHoaDonService extends GenericServiceImpl<LichSuHoaDon , UUID> {
    public LichSuHoaDonService(JpaRepository<LichSuHoaDon, UUID> repository, HoaDonRepository hoaDonRepository) {
        super(repository);
        this.hoaDonRepository = hoaDonRepository;
    }

    private final HoaDonRepository hoaDonRepository;

    public LichSuHoaDon createLichSuHoaDon(UUID idHoaDon , String trangThai , String moTa) { 
        LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).orElse(null);
        lichSuHoaDon.setIdHoaDon(hoaDon);
        lichSuHoaDon.setMoTa(moTa);
        lichSuHoaDon.setTrangThai(trangThai);
        lichSuHoaDon.setNgayTao(Instant.now());
        lichSuHoaDon.setDeleted(false);
        return repository.save(lichSuHoaDon);
    }

    public List<LichSuHoaDon> getLichSuHoaDonById(UUID idHoaDon) {
        return repository.findAll().stream()
                .filter(ls -> ls.getIdHoaDon().getId().equals(idHoaDon))
                .sorted((ls1, ls2) -> ls2.getNgayTao().compareTo(ls1.getNgayTao()))
                .collect(Collectors.toList());
    }
}
