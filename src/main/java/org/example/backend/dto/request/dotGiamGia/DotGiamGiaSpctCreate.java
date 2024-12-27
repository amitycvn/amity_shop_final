package org.example.backend.dto.request.dotGiamGia;

import lombok.Builder;

import java.util.List;
import java.util.UUID;
@Builder

public class DotGiamGiaSpctCreate {
    private DotGiamGiaUpdate dotGiamGiaUpdate;
    private List<UUID> idSpcts;

    public DotGiamGiaSpctCreate() {
    }



    public List<UUID> getIdSpcts() {
        return idSpcts;
    }

    public void setIdSpcts(List<UUID> idSpcts) {
        this.idSpcts = idSpcts;
    }

    public DotGiamGiaUpdate getDotGiamGiaUpdate() {
        return dotGiamGiaUpdate;
    }

    public void setDotGiamGiaUpdate(DotGiamGiaUpdate dotGiamGiaUpdate) {
        this.dotGiamGiaUpdate = dotGiamGiaUpdate;
    }

    public DotGiamGiaSpctCreate(DotGiamGiaUpdate dotGiamGiaUpdate, List<UUID> idSpcts) {
        this.dotGiamGiaUpdate = dotGiamGiaUpdate;
        this.idSpcts = idSpcts;
    }
}
