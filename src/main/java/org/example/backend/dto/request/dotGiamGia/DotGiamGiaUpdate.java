package org.example.backend.dto.request.dotGiamGia;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


public class DotGiamGiaUpdate {
    private UUID id;
    private String ma;
    private String ten;
    private BigDecimal giaTri;
    private Instant ngayBatDau;
    private Instant ngayKetThuc;
    private Boolean loai;
    private String trangThai;
    private String hinhThuc;
    private Integer dieuKien;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public BigDecimal getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(BigDecimal giaTri) {
        this.giaTri = giaTri;
    }

    public Instant getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Instant ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Instant getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Instant ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }


    public Boolean getLoai() {
        return loai;
    }

    public void setLoai(Boolean loai) {
        this.loai = loai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public DotGiamGiaUpdate(UUID id, String ma, String ten, BigDecimal giaTri, Instant ngayBatDau, Instant ngayKetThuc, Boolean loai, String trangThai, String hinhThuc, Integer dieuKien) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.loai = loai;
        this.trangThai = trangThai;
        this.hinhThuc = hinhThuc;
        this.dieuKien = dieuKien;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public Integer getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(Integer dieuKien) {
        this.dieuKien = dieuKien;
    }

    public DotGiamGiaUpdate() {
    }
}
