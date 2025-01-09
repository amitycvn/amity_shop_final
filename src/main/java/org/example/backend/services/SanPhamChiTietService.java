package org.example.backend.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.backend.common.PageResponse;
import org.example.backend.constants.Status;
import org.example.backend.dto.request.sanPhamV2.SanPhamChiTietRequest;
import org.example.backend.dto.request.sanPhamV2.SanPhamChiTietV2Request;
import org.example.backend.dto.response.NhanVien.NhanVienRespon;
import org.example.backend.dto.response.banHang.banHangClientResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.sanPhamV2.SanPhamChiTietDTO;
import org.example.backend.dto.response.sanPhamV2.SanPhamChiTietResponse;
import org.example.backend.models.DanhMuc;
import org.example.backend.models.DeGiay;
import org.example.backend.models.Hang;
import org.example.backend.models.MauSac;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.models.SanPham;
import org.example.backend.models.SanPhamChiTiet;
import org.example.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SanPhamChiTietService extends GenericServiceImpl<SanPhamChiTiet, UUID> {
    @Autowired
    private HangRepository hangRepository;
    @Autowired
    private DeGiayRepository deGiayRepository;
    @Autowired
    private DanhMucRepository danhMucRepository;
    @Autowired
    private LopLotRepository lopLotRepository;
    @Autowired
    private ChatLieuRepository chatLieuRepository;

    public SanPhamChiTietService(JpaRepository<SanPhamChiTiet, UUID> repository, SanPhamChiTietRepository SPCTRepository) {
        super(repository);
        this.SPCTRepository = SPCTRepository;
    }

    private final SanPhamChiTietRepository SPCTRepository;

    public PageResponse<List<banHangClientResponse>> getbanHangClient(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<banHangClientResponse> bhPage = SPCTRepository.getBanHangClient(pageable);
        return PageResponse.<List<banHangClientResponse>>builder()
                .page(bhPage.getNumber())
                .size(bhPage.getSize())
                .totalPage(bhPage.getTotalPages())
                .items(bhPage.getContent()).build();
    }

    public PageResponse<List<banHangClientResponse>> getbanHangClientbyIDDGG(int page, int size,UUID id) {
        Pageable pageable = PageRequest.of(page, size);
        Page<banHangClientResponse> bhPage = SPCTRepository.getBanHangClientbyIDDGG(pageable,id);
        return PageResponse.<List<banHangClientResponse>>builder()
                .page(bhPage.getNumber())
                .size(bhPage.getSize())
                .totalPage(bhPage.getTotalPages())
                .items(bhPage.getContent()).build();
    }

    public PageResponse<List<banHangClientResponse>> searchBanHangClient(
            int page, int itemsPerPage,
            String tenSp, String tenKichThuoc, String tenMauSac,String tenDanhMuc,String tenHang,
            BigDecimal giaMin, BigDecimal giaMax
    ) {
        Pageable pageable = PageRequest.of(page, itemsPerPage);
        Page<banHangClientResponse> results = SPCTRepository.searchBanHangClient(
                tenSp, tenKichThuoc, tenMauSac, giaMin, giaMax, pageable
        );

        return PageResponse.<List<banHangClientResponse>>builder()
                .page(results.getNumber())
                .size(results.getSize())
                .totalPage(results.getTotalPages())
                .items(results.getContent()).build();
    }


    public List<banHangClientResponse> getTop5SanPhamMoiNhat() {
        return SPCTRepository.getTop5SanPhamMoiNhat();
    }

    public List<banHangClientResponse> getSanPhamGiamGia(List<String> trangThais, UUID id) {
        return SPCTRepository.showGiamGiaTheoSp(trangThais, id);
    }

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private MauSacRepository mauSacRepository;
    @Autowired
    private KichThuocRepository kichThuocRepository;

    public List<SanPhamChiTiet> generateSanPhamChiTiet(SanPhamChiTietRequest request) {
        List<SanPhamChiTiet> sanPhamChiTiets = new ArrayList<>();
        Hang hang = hangRepository.findById(request.getHang()).orElse(null);
        DeGiay deGiay = deGiayRepository.findById(request.getDeGiay()).orElse(null);
        DanhMuc danhMuc = danhMucRepository.findById(request.getDanhMuc()).orElse(null);
        for (UUID mauSacId : request.getMauSacIds()) {
            for (UUID kichThuocId : request.getKichThuocIds()) {
                SanPhamChiTiet chiTiet = new SanPhamChiTiet();
                chiTiet.setIdSanPham(sanPhamRepository.findById(request.getIdSanPham()).orElse(null));
                chiTiet.setIdMauSac(mauSacRepository.findById(mauSacId).orElse(null));
                chiTiet.setIdKichThuoc(kichThuocRepository.findById(kichThuocId).orElse(null));
                chiTiet.setGiaBan(request.getGiaBan());
                chiTiet.setSoLuong(request.getSoLuong());
                chiTiet.setMoTa(request.getMoTa());
                chiTiet.setTen(chiTiet.getIdSanPham().getTen()+" "+chiTiet.getIdKichThuoc().getTen()+" "+chiTiet.getIdMauSac().getTen());
                sanPhamChiTiets.add(chiTiet);
            }
        }
        return sanPhamChiTietRepository.saveAll(sanPhamChiTiets);
    }

    public List<SanPhamChiTiet> generateSanPhamChiTietV2(SanPhamChiTietV2Request request) {
        SanPham sanPham = new SanPham();
        sanPham.setIdLopLot(lopLotRepository.findById(request.getIdLopLot()).orElse(null));
        sanPham.setIdChatLieu(chatLieuRepository.findById(request.getIdChatLieu()).orElse(null));
        sanPham.setIdHang(hangRepository.findById(request.getIdHang()).orElse(null));
        sanPham.setIdDanhMuc(danhMucRepository.findById(request.getIdDanhMuc()).orElse(null));
        sanPham.setIdDeGiay(deGiayRepository.findById(request.getIdDeGiay()).orElse(null));
        sanPham.setTen(request.getTenSp());
        sanPham.setTrangThai(Status.DANG_DUOC_KHOI_TAO);
        sanPhamRepository.save(sanPham);
        List<SanPhamChiTiet> sanPhamChiTiets = new ArrayList<>();
        for (UUID mauSacId : request.getMauSacIds()) {
            for (UUID kichThuocId : request.getKichThuocIds()) {
                SanPhamChiTiet chiTiet = new SanPhamChiTiet();
                chiTiet.setIdSanPham(sanPhamRepository.findById(sanPham.getId()).orElse(null));
                chiTiet.setIdMauSac(mauSacRepository.findById(mauSacId).orElse(null));
                chiTiet.setIdKichThuoc(kichThuocRepository.findById(kichThuocId).orElse(null));
                chiTiet.setGiaBan(request.getGiaBan());
                chiTiet.setGiaNhap(request.getGiaNhap());
                chiTiet.setSoLuong(request.getSoLuong());
                chiTiet.setMoTa(request.getMoTa());
                chiTiet.setTen(chiTiet.getIdSanPham().getTen()+" - "+chiTiet.getIdKichThuoc().getTen()+" - "+chiTiet.getIdMauSac().getTen());
                chiTiet.setTrangThai(Status.DANG_DUOC_KHOI_TAO);
                sanPhamChiTiets.add(chiTiet);
            }
        }
        return sanPhamChiTietRepository.saveAll(sanPhamChiTiets);
    }

    public SanPhamChiTietResponse getSanPhamChiTietById(UUID idSanPham) {
        // Tìm sản phẩm từ repository
        SanPham sanPham = sanPhamRepository.findById(idSanPham).orElseThrow(() ->
                new EntityNotFoundException("Sản phẩm không tồn tại.")
        );



        // Tìm các sản phẩm chi tiết liên quan
        List<SanPhamChiTiet> chiTietList = sanPhamChiTietRepository.findByIdSanPhamAndTrangThai(sanPham, "Hoạt động");

        if (chiTietList.isEmpty()) {
            throw new EntityNotFoundException("Không có sản phẩm chi tiết cho sản phẩm này.");
        }

        // Chuyển đổi dữ liệu để trả về
        return new SanPhamChiTietResponse(
                sanPham.getId(),
                sanPham.getTen(),
                sanPham.getIdChatLieu() != null ? sanPham.getIdChatLieu().getTen() : "Chưa có",
                sanPham.getIdLopLot() != null ? sanPham.getIdLopLot().getTen() : "Chưa có",
                chiTietList.stream()
                        .map(item -> new SanPhamChiTietResponse.Variant(
                                item.getId(),
                                item.getTen(),
                                item.getIdMauSac() != null ? item.getIdMauSac().getTen() : "Chưa có",
                                item.getIdKichThuoc() != null ? item.getIdKichThuoc().getTen() : "Chưa có",
                                item.getGiaBan(),
                                item.getSoLuong(),
                                item.getHinhAnh(),
                                item.getDeleted()
                        ))
                        .collect(Collectors.toList())
        );
    }

    public List<SanPham> getAllSanPham(){
        return sanPhamRepository.findAllByTrangThai("Hoạt động");
    }

    public List<SanPhamChiTietDTO> mapToDTO(List<SanPhamChiTiet> entities) {
        return entities.stream().map(spct -> new SanPhamChiTietDTO(
                spct.getId(),
                spct.getIdSanPham().getTen(),
                spct.getIdMauSac() != null ? spct.getIdMauSac().getTen() : null,
                spct.getIdKichThuoc() != null ? spct.getIdKichThuoc().getTen() : null,
                spct.getGiaBan(),
                spct.getSoLuong(),
                spct.getHinhAnh()
        )).collect(Collectors.toList());
    }


    public List<SanPhamChiTietDTO> getVariantsBySanPhamId(UUID idSanPham) {
        List<SanPhamChiTiet> variants = sanPhamChiTietRepository.findAllBySanPhamId(idSanPham);
        return mapToDTO(variants);
    }

}
