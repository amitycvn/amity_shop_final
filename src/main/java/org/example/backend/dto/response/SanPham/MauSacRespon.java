package org.example.backend.dto.response.SanPham;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MauSacRespon {
    private UUID id;
    private String ma;
    private String ten;
    private String trangThai;


}
