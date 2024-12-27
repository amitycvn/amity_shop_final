package org.example.backend.dto.response.SanPham;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter

public class SanPhamClientResponse {
    private UUID idSpct;
    private UUID dotGiamGia;
    private Boolean loaiGiamGia;
    private BigDecimal giaTriGiam;
    private BigDecimal giaBan;
    private BigDecimal giaGiam;
    private BigDecimal giaSauGiam;
    private String hinhAnh;

    public SanPhamClientResponse(UUID idSpct, UUID dotGiamGia,BigDecimal giaBan , Boolean loaiGiamGia, BigDecimal giaTriGiam,String hinhAnh) {
        this.idSpct = idSpct;
        this.dotGiamGia = dotGiamGia;
        this.giaBan=giaBan;
        this.loaiGiamGia = loaiGiamGia;
        this.giaTriGiam=giaTriGiam;
        this.hinhAnh=hinhAnh;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public UUID getIdSpct() {
        return idSpct;
    }

    public void setIdSpct(UUID idSpct) {
        this.idSpct = idSpct;
    }

    public UUID getDotGiamGia() {
        return dotGiamGia;
    }

    public void setDotGiamGia(UUID dotGiamGia) {
        this.dotGiamGia = dotGiamGia;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public BigDecimal getGiaGiam() {
        if (loaiGiamGia){
            return giaTriGiam;
        }else {
            return giaBan.multiply(giaTriGiam).divide(new BigDecimal(100));
        }
    }

    public void setGiaGiam(BigDecimal giaGiam) {
        this.giaGiam = giaGiam;
    }

    public Boolean getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(Boolean loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public BigDecimal getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(BigDecimal giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public BigDecimal getGiaSauGiam() {
        return giaBan.subtract(getGiaGiam());
    }

    public void setGiaSauGiam(BigDecimal giaSauGiam) {
        this.giaSauGiam = giaSauGiam;
    }
}