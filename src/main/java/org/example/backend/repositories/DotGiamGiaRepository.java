package org.example.backend.repositories;

import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaSpctResponse;
import org.example.backend.models.DotGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DotGiamGiaRepository extends JpaRepository<DotGiamGia, UUID> {
    @Query("""
                select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
                from DotGiamGia d where d.deleted=false 
            """)
    List<DotGiamGiaResponse> getAllDotGiamGia();

    // hien thi dot giam gia ben clinet
    @Query("""
                select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
                from DotGiamGia d where d.deleted=false  and d.trangThai in :trangThais
            """)
    List<DotGiamGiaResponse> getAllDotGiamGiaClient(List<String> trangThais);

    @Query("""
                select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
                from DotGiamGia d where d.deleted=false and d.id=:id
            """)
    Optional<DotGiamGiaResponse> getAllDotGiamGiaById(UUID id);

    @Query("""
                update DotGiamGia d 
                set d.deleted=:deleted where d.id=:id 
            """)

    @Modifying
    @Transactional
    void updateDetailDotGiamGia(Boolean deleted, UUID id);

    @Query("""
    select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
    from DotGiamGia d where d.deleted=false 
""")
    Page<DotGiamGiaResponse> getAllDotGiamGiaPaginate(Pageable pageable);


    @Query("""
    select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
    from DotGiamGia d where d.deleted=false 
""")
    Page<DotGiamGiaResponse> searchDotGiamGiaPaginat(Pageable pageable, DotGiamGiaSearch dotGiamGiaSearch);

    @Query("""
    SELECT new org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse(
           d.id, d.ma, d.ten, d.giaTri, d.ngayBatDau, d.ngayKetThuc, d.loai, d.trangThai, d.hinhThuc, d.dieuKien)
    FROM DotGiamGia d
    WHERE d.deleted = false
    AND (COALESCE(:#{#dotGiamGiaSearch.value}, '') = '' OR d.ma LIKE %:#{#dotGiamGiaSearch.value}% OR d.ten LIKE %:#{#dotGiamGiaSearch.value}%)
    AND (COALESCE(:#{#dotGiamGiaSearch.minNgay}, null) IS NULL OR d.ngayBatDau >= :#{#dotGiamGiaSearch.minNgay})
    AND (COALESCE(:#{#dotGiamGiaSearch.maxNgay}, null) IS NULL OR d.ngayKetThuc <= :#{#dotGiamGiaSearch.maxNgay})
    AND (COALESCE(:#{#dotGiamGiaSearch.trangThai}, '')  = '' OR d.trangThai = :#{#dotGiamGiaSearch.trangThai})
    AND (COALESCE(:#{#dotGiamGiaSearch.minGia}, null) IS NULL OR d.giaTri >= :#{#dotGiamGiaSearch.minGia})
    AND (COALESCE(:#{#dotGiamGiaSearch.maxGia}, null) IS NULL OR d.giaTri <= :#{#dotGiamGiaSearch.maxGia})
    """)
    Page<DotGiamGiaResponse> searchDotGiamGiaPaginate(Pageable pageable, DotGiamGiaSearch dotGiamGiaSearch);

    @Query("""
    select new org.example.backend.dto.response.dotGiamGia.DotGiamGiaSpctResponse(
    dggspct.id, dggspct.idSpct.id, dggspct.idDotGiamGia.id
    ) from DotGiamGiaSpct dggspct
""")
    List<DotGiamGiaSpctResponse> getAllDotGiamGiaSpct();

}