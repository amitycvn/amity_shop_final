package org.example.backend.mapper.dotGiamGia;

import org.example.backend.models.DotGiamGia;
import org.example.backend.models.DotGiamGiaSpct;
import org.example.backend.models.SanPhamChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DotGiamGiaSpctMapper {

//    // Mapping từ DotGiamGiaSpct sang DotGiamGia
//    @Mappings({
//            @Mapping(source = "idDotGiamGia", target = "id")
//    })
//    DotGiamGia toDotGiamGia(DotGiamGiaSpct dotGiamGiaSpct);
//
//    // Mapping từ DotGiamGia sang DotGiamGiaSpct
//    @Mappings({
//            @Mapping(source = "id", target = "idDotGiamGia")
//    })
//    DotGiamGiaSpct toDotGiamGiaSpct(DotGiamGia dotGiamGia);
//
//    // Mapping từ DotGiamGiaSpct sang SanPhamChiTiet
//    @Mappings({
//            @Mapping(source = "idSpct", target = "id")
//    })
//    SanPhamChiTiet toSanPhamChiTiet(DotGiamGiaSpct dotGiamGiaSpct);
//
//    // Mapping từ SanPhamChiTiet sang DotGiamGiaSpct
//    @Mappings({
//            @Mapping(source = "id", target = "idSpct")
//    })
//    DotGiamGiaSpct toDotGiamGiaSpct(SanPhamChiTiet sanPhamChiTiet);
}

