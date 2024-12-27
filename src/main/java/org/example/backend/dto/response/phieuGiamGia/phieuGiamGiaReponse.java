package org.example.backend.dto.response.phieuGiamGia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class phieuGiamGiaReponse {
	private UUID id;
	private String ma;
	private String ten;
	private Boolean loai;
	private BigDecimal giaTri;
	private BigDecimal giamToiDa;
	private String mucDo;
	private Instant ngayBatDau;
	private Instant ngayKetThuc;
	private Integer soLuong;
	private Integer dieuKien;
	private String trangThai;
}
