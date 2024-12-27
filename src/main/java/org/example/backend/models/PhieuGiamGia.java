package org.example.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.constants.Status;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ColumnDefault("newid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Nationalized
    @Column(name = "ma", length = 50)
    private String ma;

    @Nationalized
    @Lob
    @Column(name = "ten")
    private String ten;

    @ColumnDefault("0")
    @Column(name = "loai")
    private Boolean loai;

    @ColumnDefault("0")
    @Column(name = "gia_tri")
    private BigDecimal giaTri;

    @ColumnDefault("0")
    @Column(name = "giam_toi_da")
    private BigDecimal giamToiDa;

    @Nationalized
    @Column(name = "muc_do", length = 50)
    private String mucDo;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_bat_dau")
    private Instant ngayBatDau;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_ket_thuc")
    private Instant ngayKetThuc;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Nationalized
    @Column(name = "dieu_kien")
    private Integer dieuKien;

    @Nationalized
    @Column(name = "nguoi_tao", length = 50)
    private String nguoiTao;

    @Nationalized
    @Column(name = "nguoi_sua", length = 50)
    private String nguoiSua;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_sua")
    private Instant ngaySua;

    @Nationalized
    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @ColumnDefault("0")
    @Column(name = "deleted")
    private Boolean deleted;

    @PrePersist
    public void prePersist() {
        // Set default creation and modification dates
        this.ngayTao = Instant.now();
        this.deleted = Boolean.FALSE;
//        this.trangThai = Status.HOAT_DONG;
//        this.hinhThuc = Status.TAT_CA;
//        this.dieuKien = Status.DIEU_KIEN;
    }

    @PreUpdate
    public void preUpdate() {
        // Update modification date when the record is updated
        this.ngaySua = Instant.now();
    }

}