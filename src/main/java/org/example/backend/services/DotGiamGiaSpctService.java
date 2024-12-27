package org.example.backend.services;

import org.example.backend.constants.Status;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.DotGiamGiaSpct;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.example.backend.repositories.DotGiamGiaSpctRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DotGiamGiaSpctService extends GenericServiceImpl<DotGiamGiaSpct, UUID> {
    public DotGiamGiaSpctService(JpaRepository<DotGiamGiaSpct, UUID> repository) {
        super(repository);
    }

    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;

    @Autowired
    private DotGiamGiaSpctRepository dotGiamGiaSpctRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    public DotGiamGia createDotGiamGiaWithSpct(DotGiamGia dotGiamGia, List<UUID> spctIds){
        DotGiamGia savedDotGiamGia = dotGiamGiaRepository.save(dotGiamGia);
        for (UUID spctId : spctIds) {
            SanPhamChiTiet spct = sanPhamChiTietRepository.findById(spctId)
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại: " + spctId));

            DotGiamGiaSpct dotGiamGiaSpct = new DotGiamGiaSpct();
            dotGiamGiaSpct.setIdDotGiamGia(savedDotGiamGia);
            dotGiamGiaSpct.setIdSpct(spct);
            dotGiamGiaSpctRepository.save(dotGiamGiaSpct);
        }
        return savedDotGiamGia;
    }

    public void setDeletedDotGiamGiaSpctById(Boolean deleted,UUID id){
        dotGiamGiaSpctRepository.setDeletedDGGSpct(deleted, id);
    }


}
