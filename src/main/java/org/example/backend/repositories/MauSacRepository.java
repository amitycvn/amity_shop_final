package org.example.backend.repositories;

import org.example.backend.dto.response.SanPham.MauSacRespon;
import org.example.backend.models.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.MauSacRespon(m.id,m.ma,m.ten,m.trangThai)
        from MauSac m
        where m.deleted=false 
""")
    List<MauSacRespon> getAll();
    @Modifying
    @Transactional
    @Query("""
        update MauSac m set m.deleted=:deleted where m.id=:id
""")
    void setDeleted(Boolean deleted,UUID id);

    @Query("""
        select new org.example.backend.dto.response.SanPham.MauSacRespon(m.id,m.ma,m.ten,m.trangThai)
        from MauSac m
        where m.deleted=false  and m.ten Like :ten
""")
    List<MauSacRespon> search(String ten);

    @Query("""
        select new org.example.backend.dto.response.SanPham.MauSacRespon(m.id,m.ma,m.ten,m.trangThai)
        from MauSac m
        where m.deleted=false 
        order by m.ngayTao DESC 
""")
    Page<MauSacRespon> phanTrang(Pageable pageable);
}