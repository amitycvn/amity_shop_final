package org.example.backend.mapper;


import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.request.nhanVien.NhanVienRequestUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.models.DotGiamGia;
import org.example.backend.models.NguoiDung;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NhanVienMapper {
//
    @Mapping(target = "id" , ignore = true)
    void createToNhanVien(NhanVienRequestAdd dto, @MappingTarget NguoiDung entity);
    @Mapping(target = "id" , ignore = true)
    void updateToNhanVien(NhanVienRequestUpdate dto , @MappingTarget NguoiDung entity);

    void getDtoFormNhanVien(@MappingTarget NhanVienRespon dto , NguoiDung entity) ;
}
