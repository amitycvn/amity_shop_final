package org.example.backend.repositories;


import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.thongKe.ThongKeResponse;

import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;


import org.example.backend.models.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {

//    @Query("""
//        select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
//            qldh.id, qldh.ma, count(hdct.idHoaDon), qldh.tongTien, qldh.idNguoiDung.ten,
//            qldh.ngayTao, qldh.loaiHoaDon, qldh.trangThai, qldh.deleted
//        )
//        from HoaDonChiTiet hdct
//        join hdct.idHoaDon qldh
//        where qldh.deleted = false
//        group by qldh.id, qldh.ma, qldh.tongTien, qldh.idNguoiDung.ten,
//                 qldh.ngayTao, qldh.loaiHoaDon, qldh.trangThai, qldh.deleted
//""")
//    Page<QuanLyDonHangRespose> getByPageHoaDon(Pageable pageable);

    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.idHoaDon.tongTien-  COALESCE(hdct.giaGiam, 0)),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.idHoaDon.tongTien-  COALESCE(hdct.giaGiam, 0))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    LEFT JOIN PhieuGiamGia pg ON hdct.idHoaDon.idPhieuGiamGia.id = pg.id
    LEFT JOIN DotGiamGia dg ON hdct.idHoaDon.idDotGiamGia.id = dg.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND FUNCTION('YEAR', hd.ngayTao) = :year
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKe(@Param("trangThai") String trangThai, @Param("year") int year);

    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.idHoaDon.tongTien-  COALESCE(hdct.giaGiam, 0)),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.idHoaDon.tongTien-  COALESCE(hdct.giaGiam, 0))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND FUNCTION('YEAR', hd.ngayTao) = :year
      AND FUNCTION('MONTH', hd.ngayTao) = :month
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKeByMonth(@Param("trangThai") String trangThai, @Param("year") int year, @Param("month") int month);

    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.idHoaDon.tongTien-  COALESCE(hdct.giaGiam, 0)),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.idHoaDon.tongTien-  COALESCE(hdct.giaGiam, 0))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND FUNCTION('YEAR', hd.ngayTao) = :year
      AND FUNCTION('MONTH', hd.ngayTao) = :month
      AND FUNCTION('DAY', hd.ngayTao) = :day
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKeByDay(@Param("trangThai") String trangThai, @Param("year") int year, @Param("month") int month,@Param("day") int day);


    @Query("""
    SELECT new org.example.backend.dto.response.thongKe.ThongKeResponse(
        hd.id,
        SUM(hdct.soLuong),
        SUM(hdct.idHoaDon.tongTien - COALESCE(hdct.giaGiam, 0)),
        SUM(hdct.soLuong * spct.giaNhap),
        (SUM(hdct.idHoaDon.tongTien - COALESCE(hdct.giaGiam, 0))) - (SUM(hdct.soLuong * spct.giaNhap)),
        hd.trangThai,
        hd.deleted)
    FROM HoaDonChiTiet hdct
    JOIN HoaDon hd ON hdct.idHoaDon.id = hd.id
    JOIN SanPhamChiTiet spct ON hdct.idSpct.id = spct.id
    WHERE hd.trangThai = :trangThai
      AND hd.deleted = false
      AND hd.ngayTao BETWEEN :startDate AND :endDate
    GROUP BY hd.id, hd.trangThai, hd.deleted
""")
    List<ThongKeResponse> getAllThongKeByDateRange(
            @Param("trangThai") String trangThai,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );











    @Query("""
        select new org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse(
        hdct.id,spct.id,hd.id,spct.idSanPham.ten,spct.idMauSac.ten,spct.idKichThuoc.ten,
        hdct.soLuong,hdct.gia,spct.hinhAnh,hdct.ngayTao,hdct.deleted)
        from HoaDonChiTiet hdct
        join hdct.idHoaDon hd
        join hdct.idSpct spct
        where hdct.deleted = false and hd.id = :idHD
""")
    Page<hoaDonChiTietReponse> getByPageHoaDonChiTiet(Pageable pageable,UUID idHD);

    @Query("""
    select new org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse(
    hdct.id, spct.id, hd.id, spct.idSanPham.ten, spct.idMauSac.ten, spct.idKichThuoc.ten,
    hdct.soLuong, hdct.gia, spct.hinhAnh, hdct.ngayTao, hdct.deleted)
    from HoaDonChiTiet hdct
    join hdct.idHoaDon hd
    join hdct.idSpct spct
    where hdct.deleted = false and hd.ma = :ma
""")
    List<hoaDonChiTietReponse> getByMaHoaDonChiTiet(String ma);

    @Query(value = "SELECT TOP 5 sp.ten,spct.hinh_anh ,ms.ten,kt.ten,spct.gia_ban ,hdct.so_luong AS tong_so_luong\n" +
            "FROM hoa_don_chi_tiet hdct\n" +
            "JOIN san_pham_chi_tiet spct ON hdct.id_spct = spct.id\n" +
            "JOIN san_pham sp ON spct.id_san_pham = sp.id\n" +
            "join mau_sac ms on spct.id_mau_sac = ms.id\n" +
            "join kich_thuoc kt on spct.id_kich_thuoc = kt.id\n" +
            "ORDER BY tong_so_luong DESC;\n", nativeQuery = true)
    List<Object[]> top5SanPham();

    @Query(value = "select sp.ten,spct.hinh_anh,spct.gia_ban,spct.so_luong\n" +
            "from san_pham_chi_tiet spct \n" +
            "JOIN san_pham sp ON spct.id_san_pham = sp.id\n" +
            "where spct.so_luong <50\n"+
            "ORDER BY spct.so_luong DESC;\n", nativeQuery = true)
    List<Object[]> sanPhamSapHet();


//    @Query("""
//    SELECT TOP 5 sp.ten, SUM(hdct.soLuong) AS tong_so_luong
//    FROM HoaDonChiTiet hdct
//    JOIN SanPhamChiTiet spct ON hdct.idSpct = spct.id
//    JOIN SanPham sp ON spct.idSanPham = sp.id
//    GROUP BY sp.ten
//    ORDER BY tong_so_luong DESC
//""", nativeQuery = true)
//    List<Object[]> top5SanPham();








}