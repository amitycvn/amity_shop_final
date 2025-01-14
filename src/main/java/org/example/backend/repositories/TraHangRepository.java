package org.example.backend.repositories;

import org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose;
import org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse;
import org.example.backend.models.HoaDon;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.models.TraHang;
import org.example.backend.dto.response.traHang.TraHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TraHangRepository extends JpaRepository<TraHang, UUID> {
//     @Query("""
//     select new org.example.backend.dto.response.traHang.TraHangResponse(
//     t.id, t.idSpct, t.soLuong, t.lyDo, t.ngayTao, t.ngaySua, t.nguoiTao,
//     t.nguoiSua, t.trangThai, t.deleted
//     )
//     from TraHang t ,
//     SanPhamChiTiet sp
//     where t.deleted = false
//     """)
//     List<TraHangResponse> getAllTraHang();

//    @Query("""
//                select new org.example.backend.dto.response.traHang.TraHangResponse(
//                    t.id, t.idSanPhamChiTiet.id, spct.idSanPham.ten, spct.idMauSac.ten, spct.idKichThuoc.ten,
//                    t.soLuong, t.lyDo, t.ngayTao, t.ngaySua, spct.nguoiTao, spct.nguoiSua, t.trangThai,
//                    spct.hinhAnh, t.deleted
//                )
//                from TraHang t
//                left join SanPhamChiTiet spct on t.idSpct.id = spct.id
//                where t.deleted = false and t.nguoiTao = :nguoiTao
//            """)
//    List<TraHangResponse> getAllTraHangClient(String nguoiTao);
//
//    // // Tìm các hóa đơn kh đã mua theo ID người dùng
//    @Query("""
//            select new org.example.backend.dto.response.quanLyDonHang.QuanLyDonHangRespose(
//                    hd.id, hd.ma,hd.idNguoiDung.id, hd.idNguoiDung.ten, hd.soDienThoai, hd.diaChi, hd.tongTien, hd.loaiHoaDon, hd.ngayTao, hd.trangThai, hd.deleted
//                )
//                from HoaDon hd
//                where hd.deleted = false and hd.idNguoiDung.id = :idNguoiDung
//                order by hd.ngayTao desc
//            """)
//    List<QuanLyDonHangRespose> getHoaDonByIdKh(UUID idNguoiDung);
//
//    @Query("""
//                select new org.example.backend.dto.response.quanLyDonHang.hoaDonChiTietReponse(
//                    hdct.id, spct.id, hd.id, spct.idSanPham.ten, spct.idMauSac.ten, spct.idKichThuoc.ten,
//                    hdct.soLuong, hdct.gia, spct.hinhAnh, hdct.ngayTao, hdct.deleted)
//                from HoaDonChiTiet hdct
//                join hdct.idHoaDon hd
//                join hdct.idSpct spct
//                where hdct.deleted = false and hdct.id =:id
//            """)
//    List<hoaDonChiTietReponse> getHoaDonCtByID(UUID id);

    TraHang findTraHangByIdNguoiDungAndIdHoaDonAndIdSanPhamChiTiet(NguoiDung nguoiDung, HoaDon hoaDon, SanPhamChiTiet sanPhamChiTiet);

    List<TraHang> findAllByIdHoaDon(HoaDon hoaDon);

    List<TraHang> findAllByIdNguoiDung(NguoiDung nguoiDung);
}
