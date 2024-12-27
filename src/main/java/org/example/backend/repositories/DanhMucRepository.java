package org.example.backend.repositories;

import org.example.backend.dto.response.SanPham.DanhMucRespon;
import org.example.backend.models.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {
    @Query("""
        select new org.example.backend.dto.response.SanPham.DanhMucRespon(d.id,d.ma,d.ten,d.trangThai)
        from DanhMuc d 
        where d.deleted=false 
""")
    List<DanhMucRespon> getAll();

    @Modifying
    @Transactional
    @Query("""
    update DanhMuc d set d.deleted=:deleted where d.id=:id
    """)
    void setdeleted(Boolean deleted,UUID id);

    @Query("""
        select new org.example.backend.dto.response.SanPham.DanhMucRespon(d.id,d.ma,d.ten,d.trangThai)
        from DanhMuc d 
        where d.deleted=false  and d.ten Like :ten
""")
    List<DanhMucRespon> search(String ten);

    @Query("""
        select new org.example.backend.dto.response.SanPham.DanhMucRespon(d.id,d.ma,d.ten,d.trangThai)
        from DanhMuc d 
        where d.deleted=false 
        order by d.ngayTao DESC
""")
    Page<DanhMucRespon> phanTrang(Pageable pageable);
}