package org.example.backend.mapper.dotGiamGia;

import org.example.backend.dto.request.dotGiamGia.DotGiamGiaCreate;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.DotGiamGia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DotGiamGiaMapper {

    @Mapping(target = "id", ignore = true)
    void updateDotGiamGiaFromDto(DotGiamGiaUpdate dto, @MappingTarget DotGiamGia entity);

    @Mapping(target = "id", ignore = true)
    void createDotGiamGiaFromDto(DotGiamGiaCreate dto, @MappingTarget DotGiamGia entity);

    void getDtoFromDotGiamGia(@MappingTarget DotGiamGiaResponse dto, DotGiamGia entity);

    DotGiamGiaCreate switchDotGiamGiaCreateFromUpdate(DotGiamGiaUpdate entity);
}
