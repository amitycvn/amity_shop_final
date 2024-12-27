package org.example.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @ColumnDefault("newid()")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private NguoiDung idNguoiDung;

    @Nationalized
    @Column(name = "ten_nguoi_nhan", length = 50)
    private String tenNguoiNhan;

    @Nationalized
    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Nationalized
    @Column(name = "ixa", length = 50)
    private String ixa;

    @Nationalized
    @Column(name = "ihuyen", length = 50)
    private String ihuyen;

    @Nationalized
    @Column(name = "ithanh_pho", length = 50)
    private String ithanhPho;

    @Nationalized
    @Column(name = "dia_chi")
    private String diaChi;

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
    @Column(name = "trang_thai")
    private String trangThai;

    @ColumnDefault("0")
    @Column(name = "deleted")
    private Boolean deleted;

}