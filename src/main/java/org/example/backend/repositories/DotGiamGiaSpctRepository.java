package org.example.backend.repositories;

import org.example.backend.models.DotGiamGiaSpct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DotGiamGiaSpctRepository extends JpaRepository<DotGiamGiaSpct, UUID> {
    @Query("""
    update DotGiamGiaSpct ds set ds.deleted=:deleted where ds.id=:id
""")
    void setDeletedDGGSpct(Boolean deleted, UUID id);

    @Query(value = "SELECT spg.* FROM dot_giam_gia_spct spg " +
            "JOIN dot_giam_gia dgg ON spg.id_dot_giam_gia = dgg.id " +
            "WHERE spg.id_spct = :sanPhamChiTietId " +
            "AND dgg.ngay_bat_dau <= CURRENT_TIMESTAMP " +
            "AND dgg.ngay_ket_thuc >= CURRENT_TIMESTAMP " +
            "AND dgg.hinh_thuc = N'Theo sản phẩm'", nativeQuery = true)
    List<DotGiamGiaSpct> findActiveDiscountsByProductDetail(UUID sanPhamChiTietId);



}