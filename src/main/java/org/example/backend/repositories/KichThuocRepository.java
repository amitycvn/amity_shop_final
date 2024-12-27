package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.KichThuocRespon;
import org.example.backend.models.KichThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
    @Query("""
       select new org.example.backend.dto.response.SanPham.KichThuocRespon(k.id,k.ma,k.ten,k.trangThai)
       from KichThuoc k
       where k.deleted=false 
    """)
     List<KichThuocRespon> getAllKichThuoc();
    @Modifying
    @Transactional
    @Query("""
    update KichThuoc k set k.deleted=:deleted where k.id=:id
""")
    void deletedKichThuoc(Boolean deleted,UUID id);

    @Query("""
       select new org.example.backend.dto.response.SanPham.KichThuocRespon(k.id,k.ma,k.ten,k.trangThai)
       from KichThuoc k
       where k.deleted=false  and k.ten Like :ten
    """)
     List<KichThuocRespon> search(String ten);

    @Query("""
       select new org.example.backend.dto.response.SanPham.KichThuocRespon(k.id,k.ma,k.ten,k.trangThai)
       from KichThuoc k
       where k.deleted=false 
       order by k.ngayTao DESC 
    """)
    Page<KichThuocRespon> phanTrang(Pageable pageable);
}