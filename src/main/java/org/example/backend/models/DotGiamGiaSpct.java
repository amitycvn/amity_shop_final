package org.example.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

import java.util.UUID;

import static org.example.backend.constants.Constant.CURRENT_UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dot_giam_gia_spct")
public class DotGiamGiaSpct {
    @Id
    @ColumnDefault("newid()")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id=CURRENT_UUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_spct")
    private SanPhamChiTiet idSpct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dot_giam_gia")
    private DotGiamGia idDotGiamGia;

    @Nationalized
    @Column(name = "trang_thai")
    private String trangThai;

    @ColumnDefault("0")
    @Column(name = "deleted")
    private Boolean deleted;

    @PrePersist
    public void prePersist() {
        this.deleted = Boolean.FALSE;
        this.trangThai = Status.HOAT_DONG;
    }

}