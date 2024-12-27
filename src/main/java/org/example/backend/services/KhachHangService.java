package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class KhachHangService extends GenericServiceImpl<NguoiDung, UUID>{

    private final NguoiDungRepository nguoiDungRepository;
    private final PasswordEncoder passwordEncoder;
    public KhachHangService(JpaRepository<NguoiDung, UUID> repository, NguoiDungRepository khachHangRespository, NguoiDungRepository nguoiDungRepository, PasswordEncoder passwordEncoder){
    super(repository);
    this.khachHangRespository = khachHangRespository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.passwordEncoder =passwordEncoder;
    }
private final NguoiDungRepository khachHangRespository;

public PageResponse<List<KhachHangResponse>> getAllKhachHang(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<KhachHangResponse> khachHangPage = nguoiDungRepository.getAllKhachHang(pageable);

    return PageResponse.<List<KhachHangResponse>>builder()
            .page(khachHangPage.getNumber())
            .size(khachHangPage.getSize())
            .totalPage(khachHangPage.getTotalPages())
            .items(khachHangPage.getContent())
            .build();
}
public List<KhachHangResponse> getKhachHangById(UUID id){return nguoiDungRepository.getKhachHangById(id);}
public Page<KhachHangResponse> getAllKhachHangPage(Pageable pageable){
    return  khachHangRespository.getAllKhachHangPage(pageable);
}
    public void setDeletedKhachHang(UUID id){
khachHangRespository.deletedKhachHangStatus(id);
    }
    public PageResponse<List<KhachHangResponse>> searchKhachHang(int page, int size,String keyword, String gioiTinh, String trangThai){
        Pageable pageable = PageRequest.of(page, size);
        Page<KhachHangResponse> seacrchKhachHangPaginate = nguoiDungRepository.searchUserKhachHang(pageable, keyword,gioiTinh, trangThai);
        return PageResponse.<List<KhachHangResponse>>builder()
                .page(seacrchKhachHangPaginate.getNumber())
                .size(seacrchKhachHangPaginate.getSize())
                .totalPage(seacrchKhachHangPaginate.getTotalPages())
                .items(seacrchKhachHangPaginate.getContent()).build();
    }
    public List<KhachHangResponse> sortKhachHang(){return nguoiDungRepository.sortKhachHang();}
    public ResponseEntity<?> login(String email, String password) {
        Optional<NguoiDung> customer = nguoiDungRepository.findByEmail(email);

        if (customer.isPresent()) {
            NguoiDung nguoiDung = customer.get();
            if (passwordEncoder.matches(password, nguoiDung.getMatKhau())) {
                return ResponseEntity.ok(nguoiDung);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Email orr password not match");
            }
        } else {
            // Trả về lỗi nếu không tìm thấy tài khoản hoặc thông tin không chính xác
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email hoặc mật khẩu không đúng");
        }
    }
}
