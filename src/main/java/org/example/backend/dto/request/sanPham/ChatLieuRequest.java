package org.example.backend.dto.request.sanPham;

import java.time.Instant;

public class ChatLieuRequest {
    private String ma;


    private String ten;


    private Instant ngayTao;


    private Instant ngaySua;

    private String nguoiTao;

    private String nguoiSua;

    private String trangThai;

    public ChatLieuRequest() {
    }

    public ChatLieuRequest(String ma, String ten, Instant ngayTao, Instant ngaySua, String nguoiTao, String nguoiSua, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.trangThai = trangThai;
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

    public Instant getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Instant ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Instant getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Instant ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
