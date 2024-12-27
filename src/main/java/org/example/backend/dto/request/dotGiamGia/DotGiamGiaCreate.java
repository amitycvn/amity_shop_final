package org.example.backend.dto.request.dotGiamGia;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Instant;


@Builder

public class DotGiamGiaCreate {
    @NotBlank(message = "Mã không được để trống")
    private String ma;

    @NotBlank(message = "Tên không được để trống")
    private String ten;

    @NotNull(message = "Giá trị không được để trống")
    @Positive(message = "Giá trị phải lớn hơn 0")
    private BigDecimal giaTri;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private Instant ngayBatDau;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private Instant ngayKetThuc;

    @NotNull(message = "Loại không được để trống")
    private Boolean loai;

    @NotBlank(message = "Trạng thái không được để trống")
    private String trangThai;

    @NotBlank(message = "Hình thức không được để trống")
    private String hinhThuc;

    @NotNull(message = "Điều kiện không được để trống")
    private Integer dieuKien;


    public DotGiamGiaCreate(String ma, String ten, BigDecimal giaTri, Instant ngayBatDau, Instant ngayKetThuc, Boolean loai, String trangThai, String hinhThuc, Integer dieuKien) {
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

    public DotGiamGiaCreate() {
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


    @Override
    public String toString() {
        return "DotGiamGiaRequest{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", giaTri=" + giaTri +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", loai=" + loai +
                '}';
    }


    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
}
