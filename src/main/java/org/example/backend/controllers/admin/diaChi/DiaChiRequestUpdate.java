package org.example.backend.controllers.admin.diaChi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DiaChiRequestUpdate {
    private UUID id;

    private String tenNguoiNhan;

    private String soDienThoai;

    private String ixa;

    private String ihuyen;

    private String ithanhPho;

    private String diaChi;
}
