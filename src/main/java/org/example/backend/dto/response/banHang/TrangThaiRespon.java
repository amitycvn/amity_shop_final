package org.example.backend.dto.response.banHang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrangThaiRespon {

    private String trangThai;
    private Long count;
}
