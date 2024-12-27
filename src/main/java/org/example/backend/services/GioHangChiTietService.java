package org.example.backend.services;

import org.example.backend.models.GioHang;
import org.example.backend.models.GioHangChiTiet;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.example.backend.repositories.GioHangChiTietRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GioHangChiTietService extends GenericServiceImpl<GioHangChiTiet, UUID> {
    public GioHangChiTietService(JpaRepository<GioHangChiTiet, UUID> repository) {
        super(repository);
//        this.gioHangChiTietRepository = gioHangChiTietRepository;
    }
//    private final GioHangChiTietRepository gioHangChiTietRepository;

}
