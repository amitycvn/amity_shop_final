package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.LopLotRepon;
import org.example.backend.models.LopLot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LopLotRepository extends JpaRepository<LopLot, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.LopLotRepon(l.id,l.ma,l.ten,l.trangThai)
        from LopLot l 
        where l.deleted=false 
""")
    List<LopLotRepon> getAll();

    @Modifying
    @Transactional
    @Query("""
        update LopLot l set l.deleted=:deleted where l.id=:id 
""")
    void setdeleted(Boolean deleted,UUID id);

    @Query("""
        select new org.example.backend.dto.response.SanPham.LopLotRepon(l.id,l.ma,l.ten,l.trangThai)
        from LopLot l 
        where l.deleted=false and l.ten Like :ten
""")
    List<LopLotRepon> search(String ten);

    @Query("""
        select new org.example.backend.dto.response.SanPham.LopLotRepon(l.id,l.ma,l.ten,l.trangThai)
        from LopLot l 
        where l.deleted=false 
        order by l.ngayTao DESC 
""")
    Page<LopLotRepon> phanTrang(Pageable pageable);
}