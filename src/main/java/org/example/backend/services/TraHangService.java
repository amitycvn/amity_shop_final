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

}
