package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.request.sanPham.SanPhamChiTietSearchRequest;
import org.example.backend.dto.request.sanPham.SanPhamChiTietSearchRequest2;
import org.example.backend.dto.response.SanPham.SanPhamChiTietAdminResponse;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.dto.response.SanPham.SanPhamClientResponse;
import org.example.backend.dto.response.banHang.banHangClient;
import org.example.backend.dto.response.banHang.banHangClientResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.models.KichThuoc;
import org.example.backend.models.MauSac;
import org.example.backend.models.SanPham;
import org.example.backend.models.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
    @Query("""
                    select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
                    s.idSanPham.ten,s.idMauSac.id,
                    s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
                    s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
                    )
                    from SanPhamChiTiet  s
                    where s.deleted=false
            """)
    List<SanPhamChiTietRespon> getAll();

    @Modifying
    @Transactional
    @Query("""
                    update SanPhamChiTiet s set s.deleted=:deleted where s.id=:id
            """)
    void setDeleted(Boolean deleted, UUID id);

    @Query("""
                    select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
                    s.ten,s.idMauSac.id,
                    s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
                    s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
                    )
                    from SanPhamChiTiet s
                    where s.deleted=false and s.trangThai =:trangThai

                    AND (COALESCE(:#{#SPCTSearch.kichThuoc}, '') ='' OR s.idKichThuoc.ten LIKE %:#{#SPCTSearch.kichThuoc}%)
                    AND (COALESCE(:#{#SPCTSearch.mauSac}, '') ='' OR s.idMauSac.ten LIKE %:#{#SPCTSearch.mauSac}%)

            """)
    Page<SanPhamChiTietRespon> search(Pageable pageable, SanPhamChiTietSearchRequest SPCTSearch, String trangThai);

    // tim kiếm spct trong tất cả các spct--bán hàng
    @Query("""
                SELECT new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id, s.idSanPham.id,
                s.ten, s.idMauSac.id,
                s.idMauSac.ten, s.idKichThuoc.id, s.idKichThuoc.ten,
                s.soLuong, s.giaBan, s.giaNhap, s.trangThai, s.hinhAnh, s.moTa)
                FROM SanPhamChiTiet s
                WHERE s.deleted = false
                AND (:#{#SPCTSearch.kichThuoc} IS NULL OR s.idKichThuoc.ten LIKE %:#{#SPCTSearch.kichThuoc}%)
                AND (:#{#SPCTSearch.mauSac} IS NULL OR s.idMauSac.ten LIKE %:#{#SPCTSearch.mauSac}%)
                AND (:#{#SPCTSearch.tenSanPhamChiTiet} IS NULL OR s.ten LIKE %:#{#SPCTSearch.tenSanPhamChiTiet}%)
                AND (:#{#SPCTSearch.trangThai} IS NULL OR s.trangThai = :#{#SPCTSearch.trangThai})
            """)
    Page<SanPhamChiTietRespon> search2(Pageable pageable, SanPhamChiTietSearchRequest2 SPCTSearch);

    @Query("""
                    select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
                    s.ten,s.idMauSac.id,
                    s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
                    s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
                    )
                    from SanPhamChiTiet  s
                    where s.deleted=false
                    order by s.ngayTao DESC
            """)
    Page<SanPhamChiTietRespon> phanTrang(Pageable pageable);

    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamClientResponse(
                                           dggspct.idSpct.id, dggspct.idDotGiamGia.id, dggspct.idSpct.giaBan, dggspct.idDotGiamGia.loai, dggspct.idDotGiamGia.giaTri,dggspct.idSpct.hinhAnh
                                       )
                                       from
                                        DotGiamGiaSpct dggspct

            """)
    Page<SanPhamClientResponse> getAllSpctAndDgg(Pageable pageable);

    @Query("""
                     select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
                    s.idSanPham.ten,s.idMauSac.id,
                    s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
                    s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
                    )
                    from SanPhamChiTiet  s
                    where s.deleted=false  and s.idSanPham.id =:idSanPham

            """)
    List<SanPhamChiTietRespon> findByIdSpct(UUID idSanPham);

    // tìm kiếm spct trong spct
    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id, s.idSanPham.id,
                    s.idSanPham.ten, s.idMauSac.id, s.idMauSac.ten, s.idKichThuoc.id, s.idKichThuoc.ten,
                    s.soLuong, s.giaBan, s.giaNhap, s.trangThai, s.hinhAnh, s.moTa)
                from SanPhamChiTiet s
                where s.deleted = false
                and s.idSanPham.id = :#{#SPCTSearch.idSanPham}
                AND (:#{#SPCTSearch.kichThuoc} IS NULL OR s.idKichThuoc.ten LIKE %:#{#SPCTSearch.kichThuoc}%)
                AND (:#{#SPCTSearch.mauSac} IS NULL OR s.idMauSac.ten LIKE %:#{#SPCTSearch.mauSac}%)
                AND (:#{#SPCTSearch.tenSanPhamChiTiet} IS NULL OR s.ten LIKE %:#{#SPCTSearch.tenSanPhamChiTiet}%)
                AND (:#{#SPCTSearch.trangThai} IS NULL OR :#{#SPCTSearch.trangThai} = '' OR s.trangThai = :#{#SPCTSearch.trangThai})
            """)
    Page<SanPhamChiTietRespon> findByIdSpct1(Pageable pageable, SanPhamChiTietSearchRequest SPCTSearch);

    @Query("""
                     select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(s.id,s.idSanPham.id,
                    s.idSanPham.ten,s.idMauSac.id,
                    s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
                    s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
                    )
                    from SanPhamChiTiet  s
                    where s.deleted=false  and s.id =:id

            """)
    Optional<SanPhamChiTietRespon> timspctQuetQR(UUID id);

    @Query("""
                select new org.example.backend.dto.response.banHang.banHangClientResponse(
                    s.id,
                    s.idSanPham.ten as tenSp,
                    s.ten as tenSpct,
                    s.idMauSac.ten as tenMauSac,
                    s.idKichThuoc.ten as tenKichThuoc,
                    s.soLuong as soLuong,
                    COALESCE(d.id, '00000000-0000-0000-0000-000000000000') as dotGiamGia,
                    COALESCE(d.loai, false ) as loaiGiamGia,
                    COALESCE(d.giaTri, 0) as giaTriGiam,
                    s.giaBan as giaBan,
                    CASE
                        WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaGiam,
                    s.giaBan -
                    CASE
                        WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaSauGiam,
                    s.hinhAnh,
                    COALESCE(s.moTa, 'Sản Phẩm Chất Lượng') as moTa,
                    COALESCE(d.trangThai, 'Không Có') as trangThai,
                    s.ngayTao as ngayTao

                )
                from SanPhamChiTiet s

                left join DotGiamGiaSpct ds on s.id = ds.idSpct.id
                left join DotGiamGia d on d.id = ds.idDotGiamGia.id
                where s.soLuong > 0
            """)
    Page<banHangClientResponse> getBanHangClient(Pageable pageable);

    @Query("""
                select new org.example.backend.dto.response.banHang.banHangClientResponse(
                    s.id,
                    s.idSanPham.ten as tenSp,
                    s.ten as tenSpct,
                    s.idMauSac.ten as tenMauSac,
                    s.idKichThuoc.ten as tenKichThuoc,
                    s.soLuong as soLuong,
                    COALESCE(d.id, '00000000-0000-0000-0000-000000000000') as dotGiamGia,
                    COALESCE(d.loai, false ) as loaiGiamGia,
                    COALESCE(d.giaTri, 0) as giaTriGiam,
                    s.giaBan as giaBan,
                    CASE
                        WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaGiam,
                    s.giaBan -
                    CASE
                        WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaSauGiam,
                    s.hinhAnh,
                    COALESCE(s.moTa, 'Sản Phẩm Chất Lượng') as moTa,
                    COALESCE(d.trangThai, 'Không Có') as trangThai,
                    s.ngayTao as ngayTao

                )
                from SanPhamChiTiet s
                left join DotGiamGiaSpct ds on s.id = ds.idSpct.id
                left join DotGiamGia d on d.id = ds.idDotGiamGia.id
                where d.id =:id
            """)
    Page<banHangClientResponse> getBanHangClientbyIDDGG(Pageable pageable, UUID id);

    // tim kiem spct ben client

    @Query("""
                select new org.example.backend.dto.response.banHang.banHangClientResponse(
                    s.id,
                    s.idSanPham.ten as tenSp,
                    s.ten as tenSpct,
                    s.idMauSac.ten as tenMauSac,
                    s.idKichThuoc.ten as tenKichThuoc,
                    s.soLuong as soLuong,
                    COALESCE(d.id, '00000000-0000-0000-0000-000000000000') as dotGiamGia,
                    COALESCE(d.loai, false) as loaiGiamGia,
                    COALESCE(d.giaTri, 0) as giaTriGiam,
                    s.giaBan as giaBan,
                    CASE
                        WHEN COALESCE(d.loai, false) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaGiam,
                    s.giaBan -
                    CASE
                        WHEN COALESCE(d.loai, false) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaSauGiam,
                    s.hinhAnh,
                    COALESCE(s.moTa, 'Sản Phẩm Chất Lượng') as moTa,
                    COALESCE(d.trangThai, 'Không Có') as trangThai,
                    s.ngayTao as ngayTao


                )
                from SanPhamChiTiet s
                left join DotGiamGiaSpct ds on s.id = ds.idSpct.id
                left join DotGiamGia d on d.id = ds.idDotGiamGia.id
                where (:tenSp is null or s.idSanPham.ten like %:tenSp%)
                  and (:tenKichThuoc is null or s.idKichThuoc.ten like %:tenKichThuoc%)
                  and (:tenMauSac is null or s.idMauSac.ten like %:tenMauSac%)
                  and (:giaMin is null or s.giaBan >= :giaMin)
                  and (:giaMax is null or s.giaBan <= :giaMax)
            """)
    Page<banHangClientResponse> searchBanHangClient(
            @Param("tenSp") String tenSp,
            @Param("tenKichThuoc") String tenKichThuoc,
            @Param("tenMauSac") String tenMauSac,
            @Param("giaMin") BigDecimal giaMin,
            @Param("giaMax") BigDecimal giaMax,
            Pageable pageable);

    // lấy ra 5 sản phẩm mới nhất

    @Query("""
            select new org.example.backend.dto.response.banHang.banHangClientResponse(
                s.id,
                s.idSanPham.ten as tenSp,
                s.ten as tenSpct,
                s.idMauSac.ten as tenMauSac,
                s.idKichThuoc.ten as tenKichThuoc,
                s.soLuong as soLuong,
                COALESCE(d.id, '00000000-0000-0000-0000-000000000000') as dotGiamGia,
                COALESCE(d.loai, false ) as loaiGiamGia,
                COALESCE(d.giaTri, 0) as giaTriGiam,
                s.giaBan as giaBan,
                CASE
                    WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                    ELSE COALESCE(d.giaTri, 0)
                END as giaGiam,
                s.giaBan -
                CASE
                    WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                    ELSE COALESCE(d.giaTri, 0)
                END as giaSauGiam,
                s.hinhAnh,
                COALESCE(s.moTa, 'Sản Phẩm Chất Lượng') as moTa,
                COALESCE(d.trangThai, 'Không Có') as trangThai,
                s.ngayTao as ngayTao
            )
            from SanPhamChiTiet s
            left join DotGiamGiaSpct ds on s.id = ds.idSpct.id
            left join DotGiamGia d on d.id = ds.idDotGiamGia.id
            order by s.ngayTao DESC
            LIMIT 8
            """)
    List<banHangClientResponse> getTop5SanPhamMoiNhat();

    @Query("""
            select new org.example.backend.dto.response.banHang.banHangClientResponse(
                s.id,
                s.idSanPham.ten as tenSp,
                s.ten as tenSpct,
                s.idMauSac.ten as tenMauSac,
                s.idKichThuoc.ten as tenKichThuoc,
                s.soLuong as soLuong,
                COALESCE(d.id, '00000000-0000-0000-0000-000000000000') as dotGiamGia,
                COALESCE(d.loai, false ) as loaiGiamGia,
                COALESCE(d.giaTri, 0) as giaTriGiam,
                s.giaBan as giaBan,
                CASE
                    WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                    ELSE COALESCE(d.giaTri, 0)
                END as giaGiam,
                s.giaBan -
                CASE
                    WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                    ELSE COALESCE(d.giaTri, 0)
                END as giaSauGiam,
                s.hinhAnh,
                COALESCE(s.moTa, 'Sản Phẩm Chất Lượng') as moTa,
                COALESCE(d.trangThai, 'Không Có') as trangThai,
                s.ngayTao as ngayTao
            )
            from SanPhamChiTiet s
            left join DotGiamGiaSpct ds on s.id = ds.idSpct.id
            left join DotGiamGia d on d.id = ds.idDotGiamGia.id
            where  d.trangThai in :trangThais and d.id =:id
            """)
    List<banHangClientResponse> showGiamGiaTheoSp(List<String> trangThais, UUID id);

    List<SanPhamChiTiet> findByIdSanPhamAndTrangThai(SanPham idSanPham, String trangThai);

    @Query("""
                SELECT spct FROM SanPhamChiTiet spct
                JOIN FETCH spct.idSanPham sp
                LEFT JOIN FETCH spct.idMauSac ms
                LEFT JOIN FETCH spct.idKichThuoc kt
                WHERE sp.id = :idSanPham
            """)
    List<SanPhamChiTiet> findAllBySanPhamId(UUID idSanPham);

    boolean existsByIdSanPhamAndIdMauSacAndIdKichThuoc(SanPham idSanPham, MauSac idMauSac, KichThuoc idKichThuoc);

    // check số lượng spct
    // @Query("""
    // select new org.example.backend.dto.response.SanPham.SanPhamChiTietRespon(
    // s.id,s.idSanPham.id,
    // s.ten,s.idMauSac.id,
    // s.idMauSac.ten,s.idKichThuoc.id,s.idKichThuoc.ten,
    // s.soLuong,s.giaBan,s.giaNhap,s.trangThai,s.hinhAnh,s.moTa
    // )
    // from SanPhamChiTiet s
    // """)
    // int findSoLuongById(UUID productId);

    @Query("""
                    select s.soLuong
                    from SanPhamChiTiet s
                    where s.id = :productId
            """)
    Optional<Integer> findSoLuongById(UUID productId);

    @Query("""
                select new org.example.backend.dto.response.banHang.banHangClient(
                    s.id,
                    s.idSanPham.ten as tenSp,
                    s.ten as tenSpct,
                    s.idMauSac.ten as tenMauSac,
                    s.idKichThuoc.ten as tenKichThuoc,
                    s.idSanPham.idDanhMuc.ten as tenDanhMuc,
                    s.idSanPham.idHang.ten as tenHang,
                    s.soLuong as soLuong,
                    COALESCE(d.id, '00000000-0000-0000-0000-000000000000') as dotGiamGia,
                    COALESCE(d.loai, false ) as loaiGiamGia,
                    COALESCE(d.giaTri, 0) as giaTriGiam,
                    s.giaBan as giaBan,
                    CASE
                        WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaGiam,
                    s.giaBan -
                    CASE
                        WHEN COALESCE(d.loai, false ) = false THEN s.giaBan * COALESCE(d.giaTri, 0) / 100
                        ELSE COALESCE(d.giaTri, 0)
                    END as giaSauGiam,
                    s.hinhAnh,
                    COALESCE(s.moTa, 'Sản Phẩm Chất Lượng') as moTa,
                    COALESCE(d.trangThai, 'Không Có') as trangThai,
                    s.ngayTao as ngayTao

                )
                from SanPhamChiTiet s
                left join DotGiamGiaSpct ds on s.id = ds.idSpct.id
                left join DotGiamGia d on d.id = ds.idDotGiamGia.id
                where s.id =:id
            """)
    banHangClient getBanHangClientbyIDSPCT(UUID id);

}