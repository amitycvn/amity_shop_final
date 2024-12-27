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

import static org.example.backend.constants.Constant.CURRENT_UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dot_giam_gia")
public class DotGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id = CURRENT_UUID;

    @Nationalized
    @Column(name = "ma", length = 50)
    private String ma;

    @Nationalized
    @Lob
    @Column(name = "ten")
    private String ten;

    @ColumnDefault("0")
    @Column(name = "gia_tri")
    private BigDecimal giaTri;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_bat_dau")
    private Instant ngayBatDau;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_ket_thuc")
    private Instant ngayKetThuc;

    @ColumnDefault("0")
    @Column(name = "loai")
    private Boolean loai;

    @Nationalized
    @Column(name = "nguoi_sua", length = 50)
    private String nguoiSua;

    @Nationalized
    @Column(name = "nguoi_tao", length = 50)
    private String nguoiTao;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_tao")
    private Instant ngayTao;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_sua")
    private Instant ngaySua;

    @ColumnDefault("0")
    @Column(name = "deleted")
    private Boolean deleted;

    @Nationalized
    @Column(name = "trang_thai")
    private String trangThai;

    @Nationalized
    @Column(name = "hinh_thuc")
    private String hinhThuc;

    @Nationalized
    @Column(name = "dieu_kien")
    private Integer dieuKien;

    @PrePersist
    public void prePersist() {
        // Set default creation and modification dates
        this.ngayTao = Instant.now();
        this.deleted = Boolean.FALSE;
        this.trangThai = Status.HOAT_DONG;
    }

    @PreUpdate
    public void preUpdate() {
        // Update modification date when the record is updated
        this.ngaySua = Instant.now();
    }

    @Override
    public String toString() {
        return "DotGiamGia{" +
                "id=" + id +
                ", ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", giaTri=" + giaTri +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", loai=" + loai +
                ", nguoiSua='" + nguoiSua + '\'' +
                ", nguoiTao='" + nguoiTao + '\'' +
                ", ngayTao=" + ngayTao +
                ", ngaySua=" + ngaySua +
                ", deleted=" + deleted +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}