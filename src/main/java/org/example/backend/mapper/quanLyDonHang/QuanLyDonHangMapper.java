package org.example.backend.mapper.quanLyDonHang;

import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.models.HoaDonChiTiet;
import org.example.backend.models.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuanLyDonHangMapper {
    void getDtoFormQLDH(@MappingTarget QuanLyDonHangRespose dto , HoaDonChiTiet entity) ;
}
