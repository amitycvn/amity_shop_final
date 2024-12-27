package org.example.backend.dto.request.dotGiamGia;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
@Builder
@ToString
public class DotGiamGiaSearch {
    private String value;
    private Instant minNgay;
    private Instant maxNgay;
    private String trangThai;
    private BigDecimal minGia;
    private BigDecimal maxGia;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getMinNgay() {
        return minNgay;
    }

    public void setMinNgay(Instant minNgay) {
        this.minNgay = minNgay;
    }

    public Instant getMaxNgay() {
        return maxNgay;
    }

    public void setMaxNgay(Instant maxNgay) {
        this.maxNgay = maxNgay;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getMinGia() {
        return minGia;
    }

    public void setMinGia(BigDecimal minGia) {
        this.minGia = minGia;
    }

    public BigDecimal getMaxGia() {
        return maxGia;
    }

    public void setMaxGia(BigDecimal maxGia) {
        this.maxGia = maxGia;
    }

    public DotGiamGiaSearch(String value, Instant minNgay, Instant maxNgay, String trangThai, BigDecimal minGia, BigDecimal maxGia) {
        this.value = value;
        this.minNgay = minNgay;
        this.maxNgay = maxNgay;
        this.trangThai = trangThai;
        this.minGia = minGia;
        this.maxGia = maxGia;
    }

    public DotGiamGiaSearch() {
    }
}
