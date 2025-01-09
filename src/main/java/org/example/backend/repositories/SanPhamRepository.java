package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.dto.response.SanPham.SanPhamDetailRespon;
import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.models.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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


    List<SanPham> findAllByTrangThai(String trangThai);
}