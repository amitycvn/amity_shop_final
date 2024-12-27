package org.example.backend.services;

import org.example.backend.dto.request.gioHang.GioHangChiTietRequest;
import org.example.backend.dto.response.gioHang.GioHangChiTietResponse;
import org.example.backend.models.GioHang;
import org.example.backend.models.GioHangChiTiet;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.GioHangChiTietRepository;
import org.example.backend.repositories.GioHangRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GioHangService extends GenericServiceImpl<GioHang, UUID> {
    public GioHangService(JpaRepository<GioHang, UUID> repository, GioHangRepository gioHangRepository,
            GioHangChiTietRepository gioHangChiTietRepository, SanPhamChiTietRepository sanPhamChiTietRepository) {
        super(repository);
        this.gioHangRepository = gioHangRepository;
        this.gioHangChiTietRepository = gioHangChiTietRepository;
        this.sanPhamChiTietRepository = sanPhamChiTietRepository;
    }

    private final GioHangRepository gioHangRepository;
    private final GioHangChiTietRepository gioHangChiTietRepository;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;

    public GioHang finbyIDGioHang(UUID id) {
        return gioHangRepository.finbyIDKH(id);
    }

    public List<GioHangChiTietResponse> getGioHang(UUID userId) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(userId);

        GioHang gioHang = gioHangRepository.findByIdNguoiDungAndDeletedFalse(nguoiDung)
                .orElseGet(() -> {
                    GioHang newGioHang = new GioHang();
                    newGioHang.setIdNguoiDung(nguoiDung);
                    newGioHang.setNgayTao(Instant.now());
                    newGioHang.setNgaySua(Instant.now());
                    newGioHang.setDeleted(false);
                    return gioHangRepository.save(newGioHang);
                });

        return gioHangChiTietRepository.findByIdGioHangAndDeletedFalse(gioHang)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public GioHangChiTietResponse addToCart(UUID userId, GioHangChiTietRequest request) {
        // Kiểm tra và lấy thông tin người dùng
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(userId);

        // Tìm hoặc tạo giỏ hàng
        GioHang gioHang = gioHangRepository.findByIdNguoiDungAndDeletedFalse(nguoiDung)
                .orElseGet(() -> {
                    GioHang newGioHang = new GioHang();
                    newGioHang.setIdNguoiDung(nguoiDung);
                    newGioHang.setNgayTao(Instant.now());
                    newGioHang.setNgaySua(Instant.now());
                    newGioHang.setDeleted(false);
                    return gioHangRepository.save(newGioHang);
                });

        // Kiểm tra sản phẩm
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(request.getIdSanPhamChiTiet())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        // Tìm hoặc tạo chi tiết giỏ hàng
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository
                .findByIdGioHangAndIdSpctAndDeletedFalse(gioHang, sanPhamChiTiet)
                .orElseGet(() -> {
                    GioHangChiTiet newChiTiet = new GioHangChiTiet();
                    newChiTiet.setIdGioHang(gioHang);
                    newChiTiet.setIdSpct(sanPhamChiTiet);
                    newChiTiet.setDeleted(false);
                    newChiTiet.setSoLuong(0); // Khởi tạo số lượng = 0 cho giỏ hàng chi tiết mới
                    return newChiTiet;
                });

        // Tính toán số lượng mới (số lượng hiện tại + số lượng thêm vào)
        int newQuantity = gioHangChiTiet.getSoLuong() + request.getSoLuong();

        // Kiểm tra số lượng tồn
        if (sanPhamChiTiet.getSoLuong() < newQuantity) {
            throw new RuntimeException("Số lượng sản phẩm không đủ");
        }

        // Cập nhật thông tin
        gioHangChiTiet.setSoLuong(newQuantity);
        gioHangChiTiet.setThanhTien(request.getGiaBan().multiply(BigDecimal.valueOf(newQuantity)));

        return convertToResponse(gioHangChiTietRepository.save(gioHangChiTiet));
    }
    
    @Transactional
    public GioHangChiTietResponse updateToCart(UUID userId, GioHangChiTietRequest request) {
        // Kiểm tra và lấy thông tin người dùng
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(userId);

        // Tìm hoặc tạo giỏ hàng
        GioHang gioHang = gioHangRepository.findByIdNguoiDungAndDeletedFalse(nguoiDung)
                .orElseGet(() -> {
                    GioHang newGioHang = new GioHang();
                    newGioHang.setIdNguoiDung(nguoiDung);
                    newGioHang.setNgayTao(Instant.now());
                    newGioHang.setNgaySua(Instant.now());
                    newGioHang.setDeleted(false);
                    return gioHangRepository.save(newGioHang);
                });

        // Kiểm tra sản phẩm
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(request.getIdSanPhamChiTiet())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        // Kiểm tra số lượng tồn
        if (sanPhamChiTiet.getSoLuong() < request.getSoLuong()) {
            throw new RuntimeException("Số lượng sản phẩm không đủ");
        }

        // Tìm hoặc tạo chi tiết giỏ hàng
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository
                .findByIdGioHangAndIdSpctAndDeletedFalse(gioHang, sanPhamChiTiet)
                .orElseGet(() -> {
                    GioHangChiTiet newChiTiet = new GioHangChiTiet();
                    newChiTiet.setIdGioHang(gioHang);
                    newChiTiet.setIdSpct(sanPhamChiTiet);
                    newChiTiet.setDeleted(false);
                    return newChiTiet;
                });

        // Cập nhật thông tin
        gioHangChiTiet.setSoLuong(request.getSoLuong());
        gioHangChiTiet.setThanhTien(request.getGiaBan().multiply(BigDecimal.valueOf(request.getSoLuong())));

        return convertToResponse(gioHangChiTietRepository.save(gioHangChiTiet));
    }

    private GioHangChiTietResponse convertToResponse(GioHangChiTiet chiTiet) {
        return GioHangChiTietResponse.builder()
                .id(chiTiet.getId())
                .idGioHang(chiTiet.getIdGioHang().getId())
                .idSanPhamChiTiet(chiTiet.getIdSpct().getId())
                .tenSp(chiTiet.getIdSpct().getIdSanPham().getTen())
                .mauSac(chiTiet.getIdSpct().getIdMauSac().getTen())
                .kichThuoc(chiTiet.getIdSpct().getIdKichThuoc().getTen())
                .hinhAnh(chiTiet.getIdSpct().getHinhAnh())
                .donGia(chiTiet.getIdSpct().getGiaBan())
                .soLuong(chiTiet.getSoLuong())
                .thanhTien(chiTiet.getThanhTien())
                .soLuongTon(chiTiet.getIdSpct().getSoLuong())
                .build();
    }

    @Transactional
    public void deleteFromCart(UUID userId, UUID gioHangChiTietId) {
        // Kiểm tra người dùng
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setId(userId);

        // Tìm giỏ hàng của người dùng
        GioHang gioHang = gioHangRepository.findByIdNguoiDungAndDeletedFalse(nguoiDung)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        // Tìm chi tiết giỏ hàng cần xóa
        GioHangChiTiet gioHangChiTiet = gioHangChiTietRepository.findById(gioHangChiTietId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng"));

        // Kiểm tra xem chi tiết này có thuộc giỏ hàng của người dùng không
        if (!gioHangChiTiet.getIdGioHang().getId().equals(gioHang.getId())) {
            throw new RuntimeException("Sản phẩm không thuộc giỏ hàng của bạn");
        }

        // Xóa hoàn toàn khỏi database
        gioHangChiTietRepository.delete(gioHangChiTiet);
    }

}
