package org.example.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, UUID> {
    @Query("""
                select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
                p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
                 p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
                from PhieuGiamGia p where p.deleted=false 
            """)
    List<phieuGiamGiaReponse> getAllPhieuGiamGia();
    
    @Query("""
                select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
                p.id, p.ma, p.ten, p.loai, p.giaTri, p.giamToiDa, p.mucDo,
                p.ngayBatDau, p.ngayKetThuc, p.soLuong, p.dieuKien, p.trangThai)
                from PhieuGiamGia p
                left join PhieuGiamGiaChiTiet pgct on p.id = pgct.idPhieuGiamGia.id
                where p.deleted = false 
                and p.id not in (
                    select pgct2.idPhieuGiamGia.id 
                    from PhieuGiamGiaChiTiet pgct2 
                    where pgct2.idNguoiDung.id = :id 
                    and pgct2.trangThai = :daSuDung
                )
                and (p.mucDo = 'public' 
                     or (pgct.idNguoiDung.id = :id and p.mucDo = 'private'))
                and p.trangThai in (:dangDienRa, :sapDienRa)
                order by 
                    case when p.trangThai =:dangDienRa then 0 else 1 end,
                    case when p.trangThai =:dangDienRa then
                        case when p.mucDo = 'private' then 0 else 1 end
                    else 
                        case when p.mucDo = 'private' then 0 else 1 end
                    end,
                    case when p.trangThai =:dangDienRa then p.giamToiDa else 0 end desc,
                    case when p.trangThai =:sapDienRa then p.ngayBatDau end asc
            """)
    List<phieuGiamGiaReponse> getAllPhieuGiamGiabyIDKH(UUID id , String dangDienRa , String sapDienRa , String daSuDung);

    @Query("""
                update PhieuGiamGia p 
                set p.deleted=:deleted where p.id=:id 
            """)
    @Modifying
    @Transactional
    void updateDetailPhieuGiamGia(Boolean deleted, UUID id);

    @Query("""
        update PhieuGiamGia p 
        set p.trangThai = :trangThai, p.ngayKetThuc = :ngayKetThuc 
        where p.id = :id
    """)
    @Modifying
    @Transactional
    void updateTrangThaiAndNgayKetThuc(String trangThai, Instant ngayKetThuc, UUID id);

    @Query("""
            select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
                            p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
                             p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
                            from PhieuGiamGia p where p.deleted=false
                AND (COALESCE(:#{#keyFind}, '') = '' OR p.ma like %:#{#keyFind}% OR p.ten like %:#{#keyFind}%)
                AND (COALESCE(:#{#minNgay}, null) IS NULL OR p.ngayBatDau >= :#{#minNgay})
                AND (COALESCE(:#{#maxNgay}, null) IS NULL OR p.ngayKetThuc <= :#{#maxNgay})
                AND (COALESCE(:#{#trangThai}, '') = '' OR p.trangThai = :#{#trangThai})
                AND ((COALESCE(:#{#minGia}, null) IS NULL OR p.giaTri >= :#{#minGia})
                AND (COALESCE(:#{#maxGia}, null) IS NULL OR p.giaTri <= :#{#maxGia})
                AND (COALESCE(:#{#minGia}, null) IS NULL OR p.giamToiDa >= :#{#minGia})
                AND (COALESCE(:#{#maxGia}, null) IS NULL OR p.giamToiDa <= :#{#maxGia}))
                """)
    Page<phieuGiamGiaReponse> searchPhieuGiamGia(Pageable pageable, String keyFind, String trangThai,
                                                 Instant minNgay, Instant maxNgay, BigDecimal minGia, BigDecimal maxGia);

    List<PhieuGiamGia> findByNgayKetThucBeforeAndTrangThaiNot(Instant now, String trangThai);
    
    List<PhieuGiamGia> findByNgayBatDauBeforeAndNgayKetThucAfterAndTrangThaiNot(
        Instant now, 
        Instant now2, 
        String trangThai
    );
    
    List<PhieuGiamGia> findByNgayBatDauAfterAndTrangThaiNot(Instant now, String trangThai);

    //check so luong phieu giam gia
    @Query("""
    select p.soLuong
    from PhieuGiamGia p
    where p.id =:SaleId
    and p.trangThai=:trangThai
""")
    Optional<Integer> findSoLuongGiamGiaById(UUID SaleId , String trangThai);
}
