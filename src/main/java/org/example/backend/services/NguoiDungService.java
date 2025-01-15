package org.example.backend.services;

import org.example.backend.common.PageResponse;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.nhanVien.NhanVienRequestAdd;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.mapper.NhanVienMapper;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.USER_CREATE;

@Service
public class NguoiDungService extends GenericServiceImpl<NguoiDung, UUID> {
    private final NhanVienMapper nhanVienMapper;
    private final NguoiDungRepository nguoiDungRepository;
    private final PasswordEncoder passwordEncoder;

    public NguoiDungService(JpaRepository<NguoiDung, UUID> repository, NhanVienMapper nhanVienMapper, NguoiDungRepository nguoiDungRepository, PasswordEncoder passwordEncoder) {
        super(repository);

        this.nhanVienMapper = nhanVienMapper;
        this.nguoiDungRepository = nguoiDungRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public PageResponse<List<NhanVienRespon>> getAllNhanVien(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVienRespon> nhanVienPage = nguoiDungRepository.getAllNhanVien(pageable);

        return PageResponse.<List<NhanVienRespon>>builder()
                .page(nhanVienPage.getNumber())
                .size(nhanVienPage.getSize())
                .totalPage(nhanVienPage.getTotalPages())
                .items(nhanVienPage.getContent())
                .build();
    }

    public List<NhanVienRespon> getNhanVienById(UUID id) {
        return nguoiDungRepository.getNhanVienById(id);
    }

    public Page<NhanVienRespon> getAllNhanVienPage(Pageable pageable) {
        return nguoiDungRepository.getAllNhanVienPage(pageable);
    }


    public void setDeletedNhanVien(UUID id) {
        nguoiDungRepository.deleteNhanVienStatus(id);
    }

    public PageResponse<List<NhanVienRespon>> searchNhanVien(int page, int size, String keyword, String gioiTinh, String trangThai) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVienRespon> seacrchNhanVienPaginate = nguoiDungRepository.searchUserNhanVien(pageable, keyword, gioiTinh, trangThai);
        return PageResponse.<List<NhanVienRespon>>builder()
                .page(seacrchNhanVienPaginate.getNumber())
                .size(seacrchNhanVienPaginate.getSize())
                .totalPage(seacrchNhanVienPaginate.getTotalPages())
                .items(seacrchNhanVienPaginate.getContent()).build();
    }

    public PageResponse<List<NhanVienRespon>> searchNhanVienBanHangTaiQuay(int page, int size, String keyword, String trangThai) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NhanVienRespon> seacrchNhanVienPaginate = nguoiDungRepository.searchUserNhanVienBanHangTaiQuay(pageable, keyword, trangThai);
        return PageResponse.<List<NhanVienRespon>>builder()
                .page(seacrchNhanVienPaginate.getNumber())
                .size(seacrchNhanVienPaginate.getSize())
                .totalPage(seacrchNhanVienPaginate.getTotalPages())
                .items(seacrchNhanVienPaginate.getContent()).build();
    }

    public List<NhanVienRespon> sortNhanVien() {
        return nguoiDungRepository.sortNhanVien();
    }

    public ResponseEntity<?> login(String email, String password) {
        String trangThai = "Hoạt động";
        Optional<NguoiDung> user = nguoiDungRepository.findByEmail(email,trangThai);

        if (user.isPresent()) {
            NguoiDung nguoiDung = user.get();
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
    public Optional<NguoiDung> searchByEmail(String email) {
        String trangThai = "Hoạt động";
        return nguoiDungRepository.findByEmail(email,trangThai);
    }
    public Optional<NguoiDung> searchBySdt(String sdt) {
        return nguoiDungRepository.findBySdt(sdt);
    }

    public boolean checkTrangThaiUser(UUID userId) {
        String status = "Hoạt động";
        Boolean isActive = nguoiDungRepository.findTrangThaiById(userId, status);

        // Kiểm tra nếu là null hoặc không tìm thấy người dùng hợp lệ
        if (isActive == null || !isActive) {
            return false; // Người dùng không tồn tại hoặc không hoạt động
        }

        return true; // Người dùng hoạt động và không bị xóa
    }

}

