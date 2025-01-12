package org.example.backend.dto.request.diaChi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.models.NguoiDung;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DiaChiRequet {

    private UUID idNguoiDung;

    private String tenNguoiNhan;

    private String soDienThoai;

    private String ixa;

    private String ihuyen;

    private String ithanhPho;

    private String diaChi;

}
