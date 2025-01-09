package org.example.backend.dto.response.SanPham;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamResponse {
    private UUID id;
    private String ma;
    private String ten;
    private Instant ngayTao;
    private UUID idChatLieu;
    private String tenChatLieu;
    private UUID idLopLot;
    private String tenLopLot;
    private UUID idHang;
    private String tenHang;
    private UUID idDanhMuc;
    private String tenDanhMuc;
    private UUID idDeGiay;
    private String tenDeGiay;


    private String trangThai;



}
