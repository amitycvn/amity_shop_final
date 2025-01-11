package org.example.backend.repositories;

import jakarta.transaction.Transactional;
import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(
        nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, 
        nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted
    )
    from NguoiDung nd
    where nd.chucVu = 'nhanvien' and nd.deleted = false
    order by nd.ngayTao DESC
""")
    Page<NhanVienRespon> getAllNhanVien(Pageable pageable);


    @Transactional
    @Modifying
    @Query("""
    update NguoiDung nd
    set nd.deleted=true 
    where nd.id=:id
""")
    void deleteNhanVienStatus(UUID id);


    @Query("""
        select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
        from NguoiDung nd 
        where nd.chucVu = 'nhanvien' and nd.deleted = false 
        order by nd.ngayTao DESC 
    """)
    Page<NhanVienRespon> getAllNhanVienPage(Pageable pageable);



//    @Query("""
//    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
//    from NguoiDung nd
//    where (nd.ma like :name or nd.ten like :name or nd.sdt like :name)
//      and nd.chucVu = 'nhanvien'
//      and nd.deleted = false
//""")
//    List<NhanVienRespon> searchUserNhanVien(String name);
    @Query("""
        select new org.example.backend.dto.response.NhanVien.NhanVienRespon(
            nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
        from NguoiDung nd
        where nd.chucVu = 'nhanvien' 
        and nd.deleted = false
        and (
            (:keyword is null or :keyword = '' or 
            lower(nd.ten) like lower(concat('%', :keyword, '%')) or
            lower(nd.ma) like lower(concat('%', :keyword, '%')) or
            lower(nd.sdt) like lower(concat('%', :keyword, '%')))
        )
        and (:gioiTinh is null or :gioiTinh = '' or nd.gioiTinh = :gioiTinh)
        and (:trangThai is null or :trangThai = '' or nd.trangThai = :trangThai)
    """)
    Page<NhanVienRespon> searchUserNhanVien(Pageable pageable, String keyword, String gioiTinh, String trangThai);

    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(
        nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, 
        nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted
    )
    from NguoiDung nd
    where nd.chucVu = 'nhanvien' and nd.deleted = false
    and nd.id =:id
    order by nd.ngayTao DESC
""")
    List<NhanVienRespon> getNhanVienById(UUID id);


    @Query("""
    select new org.example.backend.dto.response.NhanVien.NhanVienRespon(nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
    from NguoiDung nd
    where  nd.chucVu = 'nhanvien'  and nd.deleted = false

    order by nd.ten DESC 
""")
    List<NhanVienRespon>  sortNhanVien();

//    @Transactional
//    @Modifying
//    @Query("""
//    update NguoiDung nd
//    set nd.trangThai = 'Hoat dong'
//    where nd.id = :id
//""")
//    void resetNhanVienStatusHoatDong(@Param("id") UUID id);
//
//    @Transactional
//    @Modifying
//    @Query("""
//    update NguoiDung nd
//    set nd.trangThai = 'Ngung hoat dong'
//    where nd.id = :id
//""")
//    void resetNhanVienStatusNgungHoatDong(@Param("id") UUID id);


    @Query("""
        select new org.example.backend.dto.response.NhanVien.NhanVienRespon(
            nd.id, nd.ma, nd.email, nd.sdt, nd.matKhau, nd.ten, nd.diaChi, nd.ngaySinh, nd.gioiTinh, nd.hinhAnh, nd.cccd, nd.chucVu, nd.trangThai, nd.deleted)
        from NguoiDung nd
        where nd.chucVu = 'khachhang'  and nd.sdt =:sdt
        and nd.deleted = false
        
       
    """)
    Optional<NhanVienRespon> timKiemSDT( String sdt);

    @Query("""
    select nd
    from NguoiDung nd
    where nd.email = :email and nd.deleted = false and nd.trangThai = :trangThai
""")
    Optional<NguoiDung> findByEmail(@Param("email") String email,String trangThai);

    @Query("""
    select nd
    from NguoiDung nd
    where nd.sdt = :sdt and nd.deleted = false
""")
    Optional<NguoiDung> findBySdt(@Param("sdt") String sdt);
    @Query("""
    select case when count(nd) > 0 then true else false end
    from NguoiDung nd
    where nd.id = :userId
    and nd.trangThai = :status
    and nd.deleted = false
""")
    Boolean findTrangThaiById(UUID userId, String status);













































    //    KhachHang
    @Query("""
    select new org.example.backend.dto.response.khachHang.KhachHangResponse(
    nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,
    nd.gioiTinh,nd.hinhAnh,nd.diem,nd.trangThai, nd.deleted, nd.chucVu)
    from NguoiDung nd where nd.chucVu = 'khachhang' and nd.deleted = false
""")
    Page<KhachHangResponse> getAllKhachHang(Pageable pageable);
    @Transactional
    @Modifying
    @Query("""
    update NguoiDung nd
    set nd.deleted=true 
    where nd.id=:id
""")
    void deletedKhachHangStatus(UUID id);

    @Query("""
    select new org.example.backend.dto.response.khachHang.KhachHangResponse(
    nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,
    nd.gioiTinh,nd.hinhAnh,nd.diem,nd.trangThai, nd.deleted, nd.chucVu)    
      from NguoiDung nd
        where nd.chucVu = 'khachhang' 
        and nd.deleted = false
        and (
            (:keyword is null or :keyword = '' or 
            lower(nd.ten) like lower(concat('%', :keyword, '%')) or
            lower(nd.ma) like lower(concat('%', :keyword, '%')) or
            lower(nd.sdt) like lower(concat('%', :keyword, '%')))
        )
        and (:gioiTinh is null or :gioiTinh = '' or nd.gioiTinh = :gioiTinh)
        and (:trangThai is null or :trangThai = '' or nd.trangThai = :trangThai)
    """)
    Page<KhachHangResponse> searchUserKhachHang(Pageable pageable, String keyword, String gioiTinh, String trangThai);

    @Query("""
        select new org.example.backend.dto.response.khachHang.KhachHangResponse(
        nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,
        nd.gioiTinh,nd.hinhAnh,nd.diem,nd.trangThai, nd.deleted, nd.chucVu) 
        from NguoiDung nd 
        where nd.chucVu = 'khachhang' and nd.deleted = false 
        order by nd.ngayTao DESC 
    """)
    Page<KhachHangResponse> getAllKhachHangPage(Pageable pageable);

    @Query("""
        select new org.example.backend.dto.response.khachHang.KhachHangResponse(
        nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,
        nd.gioiTinh,nd.hinhAnh,nd.diem,nd.trangThai, nd.deleted, nd.chucVu) 
        from NguoiDung nd 
        where nd.chucVu = 'khachhang' and nd.deleted = false 
        and nd.id =:id
        order by nd.ngayTao DESC 
       
    """)
    List<KhachHangResponse> getKhachHangById(UUID id);
    @Query("""
        select new org.example.backend.dto.response.khachHang.KhachHangResponse(
        nd.id,nd.ma,nd.email,nd.sdt,nd.matKhau,nd.ten,nd.diaChi,nd.ngaySinh,
        nd.gioiTinh,nd.hinhAnh,nd.diem,nd.trangThai, nd.deleted, nd.chucVu) 
        from NguoiDung nd 
        where nd.chucVu = 'khachhang' and nd.deleted = false 
        order by nd.ten DESC 
       
    """)
    List<KhachHangResponse> sortKhachHang();
    @Query("""
    select nd
    from NguoiDung nd
    where nd.ma = :ma and nd.deleted = false
""")
    Boolean findByMa(@Param("ma") String ma);

}