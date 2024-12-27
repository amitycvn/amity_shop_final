package org.example.backend.mapper.phieuGiamGia;

import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestAdd;
import org.example.backend.dto.request.phieuGiamGia.phieuGiamGiaRequestUpdate;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface phieuGiamGiaMapper {
    @Mapping(target = "id", ignore = true)
    void updatePhieuGiamGiaFromDto(phieuGiamGiaRequestUpdate dto, @MappingTarget PhieuGiamGia entity);

    @Mapping(target = "id", ignore = true)
    void createPhieuGiamGiaFromDto(phieuGiamGiaRequestAdd dto, @MappingTarget PhieuGiamGia entity);

    void getDtoFromPhieuGiamGia(@MappingTarget phieuGiamGiaReponse dto, PhieuGiamGia entity);

//    phieuGiamGiaRequestAdd switchPhieuGiamGiaCreateFromUpdate(phieuGiamGiaRequestUpdate entity);
}
