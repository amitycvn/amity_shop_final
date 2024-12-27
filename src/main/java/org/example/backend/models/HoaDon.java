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

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ColumnDefault("newid()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nguoi_dung")
    private NguoiDung idNguoiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phieu_giam_gia")
    private PhieuGiamGia idPhieuGiamGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dot_giam_gia")
    private DotGiamGia idDotGiamGia;


    @Nationalized
    @Column(name = "ma", length = 50)
    private String ma;

    @Nationalized
    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Nationalized
    @Column(name = "dia_chi")
    private String diaChi;

    @ColumnDefault("0")
    @Column(name = "gia_goc")
    private BigDecimal giaGoc;

    @ColumnDefault("0")
    @Column(name = "gia_giam")
    private BigDecimal giaGiam;

    @ColumnDefault("0")
    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Nationalized
    @Column(name = "email")
    private String email;

    @Nationalized
    @Column(name = "loai_hoa_don", length = 50)
    private String loaiHoaDon;

    @Nationalized
    @Lob
    @Column(name = "ghi_chu")
    private String ghiChu;

    @Nationalized
    @Column(name = "qr_code")
    private String qrCode;

    @ColumnDefault("0")
    @Column(name = "tien_van_chuyen")
    private BigDecimal tienVanChuyen;

    @Column(name = "ngay_du_kien_nhan")
    private Instant ngayDuKienNhan;

    @Column(name = "ngay_nhan_hang")
    private Instant ngayNhanHang;

    @Column(name = "ngay_tra_hang")
    private Instant ngayTraHang;

    @Nationalized
    @Column(name = "nhan_vien", length = 50)
    private String nhanVien;

    @ColumnDefault("getdate()")
    @Column(name = "ngay_mua")
    private Instant ngayMua;

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
        this.trangThai = Status.CHO_XAC_NHAN_HOA_DON;

    }

    @PreUpdate
    public void preUpdate() {
        // Update modification date when the record is updated
        this.ngaySua = Instant.now();
    }
}