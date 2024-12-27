package org.example.backend.mapper.khachHang;

import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.example.backend.services.KhachHangService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KhachHangMapper {
    @Mapping(target = "id", ignore = true)
    void createNguoiDungFromDto(KhachHangCreate dto,@MappingTarget NguoiDung nguoiDung);
    @Mapping(target = "id", ignore = true )
    void updateNguoiDungFromDto(KhachHangUpdate dto, @MappingTarget NguoiDung nguoiDung);
    void getDtoFromNguoiDung(@MappingTarget KhachHangResponse dto, NguoiDung nguoiDung);
    KhachHangCreate switchKhachHangCreateFromUpdate(KhachHangUpdate nguoiDung);
}
