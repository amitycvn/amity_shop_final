package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.SanPhamDetailRespon;
import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.models.ChatLieu;
import org.example.backend.models.DanhMuc;
import org.example.backend.models.DeGiay;
import org.example.backend.models.LopLot;
import org.example.backend.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamResponse(s.id,s.ma,s.ten,s.ngayTao,s.idChatLieu.id,s.idChatLieu.ten,s.idLopLot.id,s.idLopLot.ten
                ,s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.trangThai)
                from SanPham s where s.deleted= false
            """)
    List<SanPhamResponse> getAll();

    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamDetailRespon(spct.id,spct.ten,s.idChatLieu.ten,spct.idMauSac.ten,spct.idKichThuoc.ten,
                s.idLopLot.ten,spct.soLuong,spct.giaBan,spct.giaNhap,spct.trangThai,spct.hinhAnh

                )
                from SanPham s ,SanPhamChiTiet spct


                 where spct.deleted= false  and  s.id=:idSP
            """)
    List<SanPhamDetailRespon> getAllCTSPByIdSp(UUID idSP);

    @Modifying
    @Transactional
    @Query("""
                    update SanPham s set s.deleted=:deleted where s.id=:id
            """)
    void setDeleted(Boolean deleted, UUID id);

    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamResponse(s.id,s.ma,s.ten,s.ngayTao,s.idChatLieu.id,s.idChatLieu.ten,s.idLopLot.id,s.idLopLot.ten,
                s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.trangThai)
                from SanPham s
                 where s.deleted= false  and  s.ten Like :ten

            """)
    List<SanPhamResponse> search(String ten);

    @Query("""
                select new org.example.backend.dto.response.SanPham.SanPhamResponse(s.id,s.ma,s.ten,s.ngayTao,s.idChatLieu.id,s.idChatLieu.ten,s.idLopLot.id,s.idLopLot.ten,
                s.idHang.id,s.idHang.ten,s.idDanhMuc.id,s.idDanhMuc.ten,s.idDeGiay.id,s.idDeGiay.ten,s.trangThai)
                from SanPham s
                 where s.deleted= false
                 order by s.ngayTao DESC

            """)
    Page<SanPhamResponse> phanTrang(Pageable pageable);

//    Page<SanPham> findAllByMaAndTenAndIdChatLieuAndIdLopLotAndIdDeGiayAndIdDanhMuc(
//            Pageable pageable, String ma, String ten, ChatLieu idChatLieu, LopLot idLopLot, DeGiay idDeGiay, DanhMuc idDanhMuc);
@Query("SELECT sp FROM SanPham sp " +
       "WHERE (:ma IS NULL OR LOWER(sp.ma) LIKE LOWER(CONCAT('%', :ma, '%'))) " +
       "AND (:ten IS NULL OR LOWER(sp.ten) LIKE LOWER(CONCAT('%', :ten, '%'))) " +
       "AND (:idChatLieu IS NULL OR sp.idChatLieu.id = :idChatLieu) " +
       "AND (:idLopLot IS NULL OR sp.idLopLot.id = :idLopLot) " +
       "AND (:idHang IS NULL OR sp.idHang.id = :idHang) " +
       "AND (:idDeGiay IS NULL OR sp.idDeGiay.id = :idDeGiay) " +
       "AND (:idDanhMuc IS NULL OR sp.idDanhMuc.id = :idDanhMuc) " +
       "AND (:trangThai IS NULL OR sp.trangThai = :trangThai) " +
       "AND sp.deleted = FALSE")
Page<SanPham> search(
        @Param("ma") String ma,
        @Param("ten") String ten,
        @Param("idChatLieu") UUID idChatLieu,
        @Param("idLopLot") UUID idLopLot,
        @Param("idHang") UUID idHang,
        @Param("idDeGiay") UUID idDeGiay,
        @Param("idDanhMuc") UUID idDanhMuc,
        @Param("trangThai") String trangThai,
        Pageable pageable);
}