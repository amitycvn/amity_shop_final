package org.example.backend.dto.request.sanPham;

import jakarta.persistence.*;
import org.example.backend.models.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.UUID;

public class SanPhamRequest {

    private UUID id;

    private ChatLieu idChatLieu;


    private LopLot idLopLot;
    private Hang idHang;
    private DanhMuc idDanhMuc;
    private DeGiay idDeGiay;

    private String ma;

    private String ten;

    private Instant ngayTao;


    private Instant ngaySua;


    private String nguoiTao;

    private String nguoiSua;

    private String trangThai;

    private Boolean deleted;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ChatLieu getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(ChatLieu idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public LopLot getIdLopLot() {
        return idLopLot;
    }

    public void setIdLopLot(LopLot idLopLot) {
        this.idLopLot = idLopLot;
    }

    public Hang getIdHang() {
        return idHang;
    }

    public void setIdHang(Hang idHang) {
        this.idHang = idHang;
    }

    public DanhMuc getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(DanhMuc idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public DeGiay getIdDeGiay() {
        return idDeGiay;
    }

    public void setIdDeGiay(DeGiay idDeGiay) {
        this.idDeGiay = idDeGiay;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public SanPhamRequest() {
    }

    public SanPhamRequest(UUID id, ChatLieu idChatLieu, LopLot idLopLot, Hang idHang, DanhMuc idDanhMuc, DeGiay idDeGiay, String ma, String ten, Instant ngayTao, Instant ngaySua, String nguoiTao, String nguoiSua, String trangThai, Boolean deleted) {
        this.id = id;
        this.idChatLieu = idChatLieu;
        this.idLopLot = idLopLot;
        this.idHang = idHang;
        this.idDanhMuc = idDanhMuc;
        this.idDeGiay = idDeGiay;
        this.ma = ma;
        this.ten = ten;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.trangThai = trangThai;
        this.deleted = deleted;
    }
}
