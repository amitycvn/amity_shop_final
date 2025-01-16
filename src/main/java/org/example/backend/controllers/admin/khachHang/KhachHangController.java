package org.example.backend.controllers.admin.khachHang;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.Constant;
import org.example.backend.constants.Status;
import org.example.backend.dto.request.khachHang.KhachHangCreate;
import org.example.backend.dto.request.khachHang.KhachHangUpdate;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.khachHang.KhachHangResponse;
import org.example.backend.mapper.khachHang.KhachHangMapper;
import org.example.backend.models.Hang;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.example.backend.constants.api.Admin.*;

@RestController
public class KhachHangController {
    final KhachHangMapper khachHangMapper;
    final KhachHangService khachHangService;
    private final NguoiDungRepository nguoiDungRepository;
    private final Cloudinary cloudinary;
    private final PasswordEncoder passwordEncoder;

    public KhachHangController(KhachHangService khachHangService, NguoiDungRepository nguoiDungRepository,
            KhachHangMapper khachHangMapper, Cloudinary cloudinary, PasswordEncoder passwordEncoder) {
        this.khachHangService = khachHangService;
        this.nguoiDungRepository = nguoiDungRepository;
        this.khachHangMapper = khachHangMapper;
        this.cloudinary = cloudinary;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(CUSTOMER_GET_ALL)
    public ResponseEntity<ResponseData<PageResponse<List<KhachHangResponse>>>> getAllCustomer(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        PageResponse<List<KhachHangResponse>> khachHangPage = khachHangService.getAllKhachHang(page, size);
        ResponseData<PageResponse<List<KhachHangResponse>>> responseData = ResponseData
                .<PageResponse<List<KhachHangResponse>>>builder()
                .message("Get all users done")
                .status(HttpStatus.OK.value())
                .data(khachHangPage)
                .build();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(CUSTOMER_GET_BY_ID)
    public ResponseEntity<?> getKhachHangById(
            @PathVariable UUID id) {
        List<KhachHangResponse> khachHangById = khachHangService.getKhachHangById(id);
        return ResponseEntity.ok(khachHangById);
    }

    @PostMapping(CUSTOMER_UPDATE_DIACHI_BAN_HANG_CLIENT)
    public ResponseEntity<?> updateCustomerDiaChi(@PathVariable UUID id, @RequestParam("diaChi") String diaChi) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);
        System.out.println(nguoiDung);
        if (nguoiDung == null) {
            return ResponseEntity.notFound().build();
        }
        nguoiDung.setDiaChi(diaChi);
        return ResponseEntity.ok(nguoiDungRepository.save(nguoiDung));
    }

    @PostMapping(CUSTOMER_CREATE)
    public ResponseEntity<?> createCustomer(
            // @RequestParam("ma") String ma,
            @RequestParam("email") String email,
            @RequestParam("sdt") String sdt,
            @RequestParam("matKhau") String matKhau,
            @RequestParam("ten") String ten,
            @RequestParam("ngaySinh") Instant ngaySinh,
            @RequestParam("gioiTinh") String gioiTinh,
            @RequestParam("diaChi") String diaChi,
            @RequestParam(value = "hinhAnh", required = false) MultipartFile fileHinhAnh,
            @RequestParam("chucVu") String chucVu,
            @RequestParam("trangThai") String trangThai) throws IOException {
        // if (ma.trim().isEmpty()) {
        // return ResponseEntity.badRequest().body("Mã không được để trống.");
        // }
        String ma = "KH" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        if (email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email không được để trống.");
        }
        if (sdt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Số điện thoại không được để trống.");
        }
        if (matKhau.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Mật khẩu không được để trống.");
        }
        if (ten.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Tên không được để trống.");
        }
        if (diaChi.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Địa chỉ không được để trống.");
        }
        if (ngaySinh == null) {
            return ResponseEntity.badRequest().body("Ngày sinh không được để trống.");
        }
        if (gioiTinh.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Giới tính không được để trống.");
        }
        if (fileHinhAnh.isEmpty()) {
            return ResponseEntity.badRequest().body("Hình ảnh không được để trống.");
        }
        if (chucVu.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Chức vụ không được để trống.");
        }
        if (trangThai.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Trạng thái không được để trống.");
        }

        if (nguoiDungRepository.findByEmail(email, "Hoạt động").isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email đã tồn tại, vui lòng sử dụng email khác.");
        }
        if (!sdt.matches("\\d{10}")) {
            return ResponseEntity.badRequest().body("Số điện thoại phải có đúng 10 chữ số.");
        }
        if (!email.matches("^[\\w-.]+@(gmail\\.com|fpt\\.edu\\.vn)$")) {
            return ResponseEntity.badRequest()
                    .body("Email không hợp lệ. Chỉ chấp nhận email có đuôi @gmail.com hoặc @fpt.edu.vn.");
        }

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMa(ma);
        nguoiDung.setEmail(email);
        nguoiDung.setSdt(sdt);
        nguoiDung.setMatKhau(passwordEncoder.encode(matKhau));
        nguoiDung.setTen(ten);
        nguoiDung.setNgaySinh(ngaySinh);
        nguoiDung.setGioiTinh(gioiTinh);
        nguoiDung.setDiaChi(diaChi);
        if (!fileHinhAnh.isEmpty()) {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(fileHinhAnh.getBytes(),
                    ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            nguoiDung.setHinhAnh(imageUrl);
        } else {
            nguoiDung.setHinhAnh(
                    "https://res.cloudinary.com/dlwrhv088/image/upload/v1732383852/q0i7hxborl3yi0pmmswa.jpg");
        }

        nguoiDung.setChucVu(chucVu);
        nguoiDung.setTrangThai(trangThai);
        return ResponseEntity.ok(nguoiDungRepository.save(nguoiDung));

    }

    @PostMapping(CUSTOMER_CREATE_SELL)
    public ResponseEntity<?> createCustomerSell(
            @RequestParam("email") String email,
            @RequestParam("sdt") String sdt,
            @RequestParam("ten") String ten,
            @RequestParam("ngaySinh") Instant ngaySinh,
            @RequestParam("gioiTinh") String gioiTinh,
            @RequestParam("diaChi") String diaChi) throws IOException {
        String ma = "KH" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMa(ma);
        nguoiDung.setSdt(sdt);
        nguoiDung.setMatKhau(passwordEncoder.encode("a1234b5c"));
        nguoiDung.setTen(ten);
        nguoiDung.setEmail(email);
        nguoiDung.setNgaySinh(ngaySinh);
        nguoiDung.setGioiTinh(gioiTinh);
        nguoiDung.setDiaChi(diaChi);

        nguoiDung.setHinhAnh("");
        nguoiDung.setChucVu(Constant.KHACH_HANG);
        nguoiDung.setTrangThai(Status.HOAT_DONG);
        return ResponseEntity.ok(nguoiDungRepository.save(nguoiDung));

    }

    @PutMapping(CUSTOMER_UPDATE_SELL)
    public ResponseEntity<?> updateCustomerSell(
            @RequestParam("id") UUID id,
            @RequestParam("sdt") String sdt,
            @RequestParam("ten") String ten,
            @RequestParam("diaChi") String diaChi) throws IOException {

        NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);

        if (nguoiDung == null) {
            return ResponseEntity.notFound().build();
        }

        nguoiDung.setSdt(sdt);

        nguoiDung.setTen(ten);

        nguoiDung.setDiaChi(diaChi);

        return ResponseEntity.ok(nguoiDungRepository.save(nguoiDung));

    }

    @PutMapping(CUSTOMER_UPDATE)
    public ResponseEntity<?> updateCustomer(
            @PathVariable UUID id,
            @RequestParam(value = "ma", defaultValue = "") String ma,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "sdt", defaultValue = "") String sdt,
            @RequestParam(value = "ten", defaultValue = "") String ten,
            @RequestParam(value = "diaChi", defaultValue = "") String diaChi,
            @RequestParam(value = "ngaySinh", defaultValue = "") Instant ngaySinh,
            @RequestParam(value = "gioiTinh", defaultValue = "") String gioiTinh,
            @RequestParam(value = "hinhAnh", required = false) MultipartFile hinhAnh,
            @RequestParam(value = "chucVu", defaultValue = "") String chucVu,
            @RequestParam(value = "trangThai", defaultValue = "") String trangThai) throws IOException {
        Optional<NguoiDung> exitNguoiDung = khachHangService.findById(id);
        if (exitNguoiDung.isEmpty()) {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy người dùng
        }
        if (ma.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Mã không được để trống.");
        }
        if (email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email không được để trống.");
        }
        if (sdt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Số điện thoại không được để trống.");
        }
        if (ten.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Tên không được để trống.");
        }
        if (diaChi.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Địa chỉ không được để trống.");
        }
        if (ngaySinh == null) {
            return ResponseEntity.badRequest().body("Ngày sinh không được để trống.");
        }
        if (gioiTinh.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Giới tính không được để trống.");
        }
        if (chucVu.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Chức vụ không được để trống.");
        }
        if (trangThai.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Trạng thái không được để trống.");
        }
        if (!sdt.matches("\\d{10}")) {
            return ResponseEntity.badRequest().body("Số điện thoại phải có đúng 10 chữ số.");
        }
        if (!email.matches("^[\\w-.]+@(gmail\\.com|fpt\\.edu\\.vn)$")) {
            return ResponseEntity.badRequest()
                    .body("Email không hợp lệ. Chỉ chấp nhận email có đuôi @gmail.com hoặc @fpt.edu.vn.");
        }
        NguoiDung nguoiDung = exitNguoiDung.get();
        nguoiDung.setId(id);
        nguoiDung.setMa(ma);
        nguoiDung.setEmail(email);
        nguoiDung.setSdt(sdt);
        nguoiDung.setTen(ten);
        nguoiDung.setDiaChi(diaChi);
        nguoiDung.setNgaySinh(ngaySinh);
        nguoiDung.setGioiTinh(gioiTinh);

        if (hinhAnh != null && !hinhAnh.isEmpty()) {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnh.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            nguoiDung.setHinhAnh(imageUrl);
        } else {
            nguoiDung.setHinhAnh(nguoiDung.getHinhAnh());
        }

        nguoiDung.setChucVu(chucVu);
        nguoiDung.setTrangThai(trangThai);

        khachHangService.save(nguoiDung);
        return ResponseEntity.ok(nguoiDung); // Trả về đối tượng người dùng đã cập nhật
    }

    @DeleteMapping(CUSTOMER_DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id) {
        NguoiDung nd = khachHangService.findById(id).orElse(null);

        if (nd != null) {
            khachHangService.setDeletedKhachHang(id);
            return ResponseEntity.ok().body("Deleted id: " + id);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping(CUSTOMER_GET_BY_KH)
    public ResponseEntity<?> searchUserKhachHang(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "gioiTinh", defaultValue = "") String gioiTinh,
            @RequestParam(value = "trangThai", defaultValue = "") String trangThai) {
        PageResponse<List<KhachHangResponse>> result = khachHangService.searchKhachHang(page, size, keyword, gioiTinh,
                trangThai);
        ResponseData<PageResponse<List<KhachHangResponse>>> responseData = ResponseData
                .<PageResponse<List<KhachHangResponse>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(result).build();
        return ResponseEntity.ok().body(responseData);
    }

    @GetMapping(PAGE_CUSTOMER)
    public ResponseEntity<ResponseData<PageResponse<List<KhachHangResponse>>>> getAllCustomerPage(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable phanTrang = PageRequest.of(page, itemsPerPage);
        Page<KhachHangResponse> khachHangPage = khachHangService.getAllKhachHangPage(phanTrang);

        PageResponse<List<KhachHangResponse>> pageResponse = PageResponse.<List<KhachHangResponse>>builder()
                .page(khachHangPage.getNumber())
                .size(khachHangPage.getSize())
                .totalPage(khachHangPage.getTotalPages())
                .items(khachHangPage.getContent())
                .build();

        ResponseData<PageResponse<List<KhachHangResponse>>> responseData = ResponseData
                .<PageResponse<List<KhachHangResponse>>>builder()
                .message("Get paginated users done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }

    @GetMapping(CUSTOMER_GET_BY_SDT)
    public ResponseEntity<?> getAllCustomerBySDT(@RequestParam(value = "sdt", defaultValue = "") String sdt) {
        String trangThai = "Hoạt động";
        return ResponseEntity.ok(nguoiDungRepository.timKiemSDT(sdt,trangThai));
    }

    @GetMapping(CUSTOMER_SORT)
    public ResponseEntity<List<KhachHangResponse>> sortCustomerSort() {
        List<KhachHangResponse> result = khachHangService.sortKhachHang();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(CUSTOMER_LOGIN)
    public ResponseEntity<?> login(
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "password", defaultValue = "") String password) {
        return ResponseEntity.ok(khachHangService.login(email, password));
    }


}
