package org.example.backend.repositories;


import org.example.backend.dto.response.SanPham.HangRespon;
import org.example.backend.models.Hang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Native;
import java.util.List;
import java.util.UUID;

public interface HangRepository extends JpaRepository<Hang, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.HangRespon(h.id,h.ma,h.ten,h.trangThai)
        from Hang h 
        where h.deleted= false 
""")
    List<HangRespon> getAll();

    @Modifying
    @Transactional

    @Query("""
        update Hang h set h.deleted=:deleted where h.id=:id
        
""")
    void deletedHang(Boolean deleted,UUID id);

    @Query("""
        select new org.example.backend.dto.response.SanPham.HangRespon(h.id,h.ma,h.ten,h.trangThai)
        from Hang h 
        where h.deleted= false and h.ten Like :ten
""")
    List<HangRespon> search(String ten);

    @Query("""
        select new org.example.backend.dto.response.SanPham.HangRespon(h.id,h.ma,h.ten,h.trangThai)
        from Hang h 
        where h.deleted= false 
        order by h.ngayTao DESC 
""")
    Page<HangRespon> phanTrang(Pageable pageable);

}