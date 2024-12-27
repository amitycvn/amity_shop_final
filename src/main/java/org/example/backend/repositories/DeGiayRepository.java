package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.models.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DeGiayRepository extends JpaRepository<DeGiay, UUID> {

    @Query("""
    select new org.example.backend.dto.response.SanPham.DeGiayRepon(b.id,b.ma,b.ten,b.trangThai)
    from DeGiay b 
    where b.deleted=false 
""")
    List<DeGiayRepon> getAll();

    @Modifying
    @Transactional
    @Query("""
           update DeGiay d set d.deleted=:deleted where d.id=:id
""")
    void setdeleted(Boolean deleted,UUID id);

    @Query("""
    select new org.example.backend.dto.response.SanPham.DeGiayRepon(b.id,b.ma,b.ten,b.trangThai)
    from DeGiay b 
    where b.deleted=false and b.ten Like :ten
""")
    List<DeGiayRepon> search(String ten);

    @Query("""
    select new org.example.backend.dto.response.SanPham.DeGiayRepon(b.id,b.ma,b.ten,b.trangThai)
    from DeGiay b 
    where b.deleted=false
    order by b.ngayTao DESC
""")
    Page<DeGiayRepon> phanTrang(Pageable pageable);
}