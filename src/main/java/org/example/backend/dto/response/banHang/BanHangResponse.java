package org.example.backend.dto.response.banHang;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BanHangResponse {

    private UUID id;
    private String ma;
    private String trangThai;
}
