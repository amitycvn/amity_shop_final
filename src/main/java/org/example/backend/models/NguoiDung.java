package org.example.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backend.constants.Status;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.UUID;

import static org.example.backend.constants.Constant.CURRENT_UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ColumnDefault("newid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Nationalized
    @Column(name = "ma", length = 50)
    private String ma;

    @Nationalized
    @Column(name = "email", length = 50)
    private String email;

    @Nationalized
    @Column(name = "sdt", length = 20)
    private String sdt;

    @Nationalized
    @Column(name = "mat_khau", length = 100)
    private String matKhau;

    @Nationalized
    @Column(name = "ten", length = 50)
    private String ten;

    @Nationalized
    @Column(name = "dia_chi")
    private String diaChi;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_sinh")
    private Instant ngaySinh;

    @Nationalized
    @Column(name = "gioi_tinh", length = 50)
    private String gioiTinh;

    @Nationalized
    @Lob
    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Nationalized
    @Column(name = "cccd", length = 50)
    private String cccd;

    @Column(name = "diem")
    private Integer diem;

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
    @Column(name = "chuc_vu", length = 50)
    private String chucVu;

    @Nationalized
    @Column(name = "trang_thai")
    private String trangThai;

    @ColumnDefault("0")
    @Column(name = "deleted")
    private Boolean deleted;

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

}