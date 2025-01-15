package org.example.backend.controllers.admin.sanpham;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamChiTietSearchRequest;
import org.example.backend.dto.request.sanPham.SanPhamChiTietSearchRequest2;
import org.example.backend.dto.request.sanPhamV2.SanPhamChiTietRequest;
import org.example.backend.dto.request.sanPhamV2.SanPhamChiTietV2Request;
import org.example.backend.dto.response.SanPham.SanPhamChiTietRespon;
import org.example.backend.dto.response.SanPham.SanPhamClientResponse;
import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.sanPhamV2.SanPhamChiTietDTO;
import org.example.backend.dto.response.sanPhamV2.SanPhamChiTietResponse;
import org.example.backend.models.*;
import org.example.backend.repositories.DotGiamGiaSpctRepository;
import org.example.backend.repositories.SanPhamChiTietRepository;
import org.example.backend.repositories.SanPhamRepository;
import org.example.backend.services.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.*;

@RestController
public class SanPhamChiTietController {
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private DotGiamGiaSpctRepository dotGiamGiaSpctRepository;

    @GetMapping(Admin.PRODUCT_DETAIL_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sanPhamChiTietRepository.getAll());
    }

    public String saveImageFile(MultipartFile file) throws IOException {

        String uploadDir = new File("src/main/resources/static/images").getAbsolutePath();

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path filePath = Paths.get(uploadDir, fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/images/" + fileName;
    }

    @PostMapping(value = Admin.PRODUCT_DETAIL_CREATE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @PathVariable("id") UUID idSanPham,

            @RequestParam("idMauSac") MauSac idMauSac,
            @RequestParam("idKichThuoc") KichThuoc idKichThuoc,
            @RequestParam("soLuong") int soLuong,
            @RequestParam("giaBan") BigDecimal giaBan,
            @RequestParam("giaNhap") BigDecimal giaNhap,
            @RequestParam("trangThai") String trangThai,
            @RequestParam("moTa") String moTa,
            @RequestParam("hinhAnh") MultipartFile hinhAnhFile) {

        try {
            // Kiểm tra sản phẩm tồn tại
            if (!sanPhamRepository.existsById(idSanPham)) {
                return ResponseEntity.badRequest().body("Sản phẩm không tồn tại!");
            }

            // Kiểm tra biến thể đã tồn tại
            boolean exists = sanPhamChiTietRepository.existsByIdSanPhamAndIdMauSacAndIdKichThuoc(
                    sanPhamRepository.findById(idSanPham).orElse(null), idMauSac, idKichThuoc);
            if (exists) {
                return ResponseEntity.badRequest().body("Biến thể đã tồn tại trong sản phẩm chi tiết!");
            }
            String tenSpct = sanPhamRepository.findById(idSanPham).orElse(null).getTen() + " - " + idKichThuoc.getTen() + " - " + idMauSac.getTen();
            // Tạo mới sản phẩm chi tiết
            SanPhamChiTiet s = new SanPhamChiTiet();
            // Tạo tên sản phẩm chi tiết

            s.setIdSanPham(sanPhamRepository.findById(idSanPham).orElse(null));
            s.setTen(tenSpct);
            s.setIdMauSac(idMauSac);
            s.setIdKichThuoc(idKichThuoc);
            s.setSoLuong(soLuong);
            s.setGiaBan(giaBan);
            s.setGiaNhap(giaNhap);
            s.setTrangThai(trangThai);
            s.setMoTa(moTa);

            // Upload hình ảnh
            Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnhFile.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");
            s.setHinhAnh(imageUrl);

            // Lưu sản phẩm chi tiết
            return ResponseEntity.ok(sanPhamChiTietRepository.save(s));

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Tải lên hình ảnh thất bại: " + e.getMessage());
        }
    }


    @PutMapping(value = Admin.PRODUCT_DETAIL_UPDATE)
    public ResponseEntity<?> update(
            @PathVariable("id") UUID id,
            @PathVariable("idSanPham") UUID idSanPham,
            @RequestParam("idMauSac") MauSac idMauSac,
            @RequestParam("idKichThuoc") KichThuoc idKichThuoc,
            @RequestParam("soLuong") int soLuong,
            @RequestParam("giaBan") BigDecimal giaBan,
            @RequestParam("giaNhap") BigDecimal giaNhap,
            @RequestParam("trangThai") String trangThai,
            @RequestParam("moTa") String moTa,
            @RequestPart("hinhAnh")String hinhAnh) {
        try {
            SanPhamChiTiet s = sanPhamChiTietRepository.findById(id).orElse(null);

            if (s != null) {

                s.setIdSanPham(sanPhamRepository.findById(idSanPham).orElse(null));
                s.setIdMauSac(idMauSac);
                s.setIdKichThuoc(idKichThuoc);
                s.setSoLuong(soLuong);
                s.setGiaBan(giaBan);
                s.setGiaNhap(giaNhap);
                s.setTrangThai(trangThai);
                s.setMoTa(moTa);
                s.setHinhAnh(hinhAnh);

                // Tải lên hình ảnh lên Cloudinary nếu có tệp hình ảnh
//                if (hinhAnhFile != null && !hinhAnhFile.isEmpty()) {
//                    Map<String, Object> uploadResult = cloudinary.uploader().upload(hinhAnhFile.getBytes(),
//                            ObjectUtils.emptyMap());
//                    String imageUrl = (String) uploadResult.get("secure_url");
//                    s.setHinhAnh(imageUrl);
//                }

                // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
                return ResponseEntity.ok(sanPhamChiTietRepository.save(s));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sản phẩm chi tiết không tồn tại");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi xảy ra: " + e.getMessage());
        }
    }

    @PutMapping(Admin.PRODUCT_DETAIL_SET_DELETE)
    public ResponseEntity<?> updateSanPham(@PathVariable UUID id) {
        SanPhamChiTiet m = sanPhamChiTietRepository.findById(id).orElse(null);
        if (m != null) {
            sanPhamChiTietRepository.setDeleted(!m.getDeleted(), id);
            return ResponseEntity.ok("set deleted successfully id " + id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.PRODUCT_DETAIL_DETAIL)
    public ResponseEntity<?> detail(@PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamChiTietRepository.findById(id).orElse(null));
    }
    //tim kieems spct trong ban hang tại quầy
    @GetMapping(Admin.PRODUCT_DETAIL_SEARCH)
    public ResponseEntity<?> search(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(defaultValue = "ngayTao") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(defaultValue = "") String tenSanPham,
            @RequestParam(defaultValue = "") String mauSac,
            @RequestParam(defaultValue = "") String kichThuoc) {
        SanPhamChiTietSearchRequest2 searchRequest = new SanPhamChiTietSearchRequest2();
        searchRequest.setTenSanPham(tenSanPham);
        searchRequest.setMauSac(mauSac);
        searchRequest.setKichThuoc(kichThuoc);
        String trangThai = "Hoạt động";
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<SanPhamChiTietRespon> searchSPCTPaginate = sanPhamChiTietRepository.search(pageable, searchRequest,trangThai);

        for (SanPhamChiTietRespon spct : searchSPCTPaginate.getContent()) {
            spct.setGiaNhap(BigDecimal.valueOf(0));
            // Tìm tất cả các đợt giảm giá đang hoạt động
            List<DotGiamGiaSpct> discounts =
                    dotGiamGiaSpctRepository.findActiveDiscountsByProductDetail(spct.getId());
            System.out.println("discount123: " + discounts);

            BigDecimal giaGiamTotNhat = BigDecimal.ZERO;

            // Tính toán đợt giảm giá tốt nhất
            for (DotGiamGiaSpct discount : discounts) {
                DotGiamGia dotGiamGia = discount.getIdDotGiamGia();
                BigDecimal giaGiamHienTai;

                if (!dotGiamGia.getLoai()) { // Giảm giá theo tiền mawjt (loai = false)
                    giaGiamHienTai = spct.getGiaBan().multiply(dotGiamGia.getGiaTri())
                            .divide(BigDecimal.valueOf(100));
                } else { // Giảm giá theo tiền mặt
                    giaGiamHienTai = dotGiamGia.getGiaTri();
                }

                // So sánh và chọn giá giảm lớn nhất
                if (giaGiamHienTai.compareTo(giaGiamTotNhat) > 0) {
                    giaGiamTotNhat = giaGiamHienTai;
                }
            }

            // Làm tròn giá trị về số nguyên
            BigDecimal giaNhapRounded = giaGiamTotNhat.setScale(0, RoundingMode.HALF_UP);

            // Set giá trị đã làm tròn vào spct
            spct.setGiaNhap(giaNhapRounded);
        }

        PageResponse<List<SanPhamChiTietRespon>> data = PageResponse.<List<SanPhamChiTietRespon>>builder()
                .page(searchSPCTPaginate.getNumber())
                .size(searchSPCTPaginate.getSize())
                .totalPage(searchSPCTPaginate.getTotalPages())
                .items(searchSPCTPaginate.getContent()).build();
        ResponseData<PageResponse<List<SanPhamChiTietRespon>>> responseData = ResponseData
                .<PageResponse<List<SanPhamChiTietRespon>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(data).build();
        return ResponseEntity.ok(responseData);
    }

    /// tim kiem spct trong tất cả spct -- quản lí sản phẩm

    @GetMapping(Admin.PRODUCT_DETAIL_SEARCH2)
    public ResponseEntity<?> search(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(defaultValue = "ngayTao") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,

            @RequestParam(defaultValue = "") String mauSac,
            @RequestParam(defaultValue = "") String kichThuoc,
            @RequestParam(defaultValue = "") String tenSanPhamChiTiet,
            @RequestParam(required = false) String trangThai) {

        SanPhamChiTietSearchRequest searchRequest = new SanPhamChiTietSearchRequest();
        searchRequest.setMauSac(mauSac);
        searchRequest.setKichThuoc(kichThuoc);
        searchRequest.setTenSanPhamChiTiet(tenSanPhamChiTiet);
        searchRequest.setTrangThai(trangThai);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<SanPhamChiTietRespon> searchSPCTPaginate = sanPhamChiTietRepository.search2(pageable, searchRequest);

        PageResponse<List<SanPhamChiTietRespon>> data = PageResponse.<List<SanPhamChiTietRespon>>builder()
                .page(searchSPCTPaginate.getNumber())
                .size(searchSPCTPaginate.getSize())
                .totalPage(searchSPCTPaginate.getTotalPages())
                .items(searchSPCTPaginate.getContent()).build();

        ResponseData<PageResponse<List<SanPhamChiTietRespon>>> responseData = ResponseData
                .<PageResponse<List<SanPhamChiTietRespon>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(data).build();

        return ResponseEntity.ok(responseData);
    }

    @GetMapping(Admin.PRODUCT_DETAIL_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<SanPhamChiTietRespon>>>> phanTrang(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<SanPhamChiTietRespon> spctPage = sanPhamChiTietRepository.phanTrang(phanTrang);

        PageResponse<List<SanPhamChiTietRespon>> pageResponse = PageResponse.<List<SanPhamChiTietRespon>>builder()
                .page(spctPage.getNumber())
                .size(spctPage.getSize())
                .totalPage(spctPage.getTotalPages())
                .items(spctPage.getContent())
                .build();

        ResponseData<PageResponse<List<SanPhamChiTietRespon>>> responseData = ResponseData
                .<PageResponse<List<SanPhamChiTietRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }


    //tìm kiếm spct theo sp
    @GetMapping(Admin.PRODUCT_DETAIL_GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        List<SanPhamChiTietRespon> sanPhamChiTietList = sanPhamChiTietRepository.findByIdSpct(id);
        if (!sanPhamChiTietList.isEmpty()) {
            return ResponseEntity.ok(sanPhamChiTietList);
        }
        return null;
    }
    // tim kiếm spct trong spct (spct)
    @GetMapping(Admin.PRODUCT_DETAIL_GET_BY_ID1)
    public ResponseEntity<?> getSpctById(@PathVariable UUID id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(defaultValue = "ngayTao") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,

            @RequestParam(defaultValue = "") String mauSac,
            @RequestParam(defaultValue = "") String kichThuoc,
            @RequestParam(defaultValue = "") String trangThai
                                         ) {
        SanPhamChiTietSearchRequest searchRequest = new SanPhamChiTietSearchRequest();
        searchRequest.setMauSac(mauSac);
        searchRequest.setKichThuoc(kichThuoc);
        searchRequest.setTrangThai(trangThai);
        searchRequest.setIdSanPham(id);

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<SanPhamChiTietRespon> searchSPCTPaginate = sanPhamChiTietRepository.findByIdSpct1(pageable, searchRequest);
        PageResponse<List<SanPhamChiTietRespon>> data = PageResponse.<List<SanPhamChiTietRespon>>builder()
                .page(searchSPCTPaginate.getNumber())
                .size(searchSPCTPaginate.getSize())
                .totalPage(searchSPCTPaginate.getTotalPages())
                .items(searchSPCTPaginate.getContent()).build();
        ResponseData<PageResponse<List<SanPhamChiTietRespon>>> responseData = ResponseData
                .<PageResponse<List<SanPhamChiTietRespon>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(data).build();
        return ResponseEntity.ok(responseData);
    }

    // qr
    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping(Admin.PRODUCT_DETAIL_QR)
    public ResponseEntity<String> getQRCode(@PathVariable String id) throws Exception {
        String qrCode = qrCodeService.generateQRCode(id);
        return ResponseEntity.ok(qrCode);
    }

    @GetMapping(Admin.PRODUCT_DETAIL_QR_TO_CART)
    public ResponseEntity<?> spQRtoCart(@PathVariable UUID id) {
        Optional<SanPhamChiTietRespon> sanPhamChiTietList = sanPhamChiTietRepository.timspctQuetQR(id);
        if (!sanPhamChiTietList.isEmpty()) {
            return ResponseEntity.ok(sanPhamChiTietList);
        }
        return null;
    }

    @GetMapping(Admin.PRODUCT_DETAIL_CLIENT)
    public ResponseEntity<Map<String, Object>> getPaginatedProducts(
            @RequestParam(value = "itemsPerPage", defaultValue = "5") int itemsPerPage,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try {
            // Tạo đối tượng Pageable
            Pageable pageable = PageRequest.of(page, itemsPerPage);

            // Lấy danh sách sản phẩm từ repository
            // Page<SanPhamChiTietRespon> productPage =
            // sanPhamChiTietRepository.phanTrang(pageable);
            Page<SanPhamClientResponse> productPage = sanPhamChiTietRepository.getAllSpctAndDgg(pageable);

            // Chuẩn bị phản hồi dưới dạng map
            Map<String, Object> response = new HashMap<>();
            response.put("products", productPage.getContent());
            response.put("totalPages", productPage.getTotalPages());

            // Trả về phản hồi
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Trả về lỗi dưới dạng JSON
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to fetch paginated products");
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @PostMapping("/api/v1/admin/product/generate")
    public ResponseEntity<?> generateSanPhamChiTiet(@RequestBody SanPhamChiTietRequest request) {
        List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietService.generateSanPhamChiTiet(request);
        return ResponseEntity.ok(sanPhamChiTiets);
    }

    @PostMapping("/api/v1/admin/product/generate/v2")
    public ResponseEntity<?> generateSanPhamChiTietV2(@RequestBody SanPhamChiTietV2Request request) {
        List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietService.generateSanPhamChiTietV2(request);
        return ResponseEntity.ok(sanPhamChiTiets);
    }

    @GetMapping("/api/v1/admin/product/get-all")
    public ResponseEntity<?> getAllSanPhamChiTiet() {
        return ResponseEntity.ok(sanPhamChiTietRepository.findAll());
    }

    @GetMapping("/api/v1/product/{idSanPham}")
    public ResponseEntity<?> getSanPhamChiTietById(@PathVariable UUID idSanPham) {
        SanPhamChiTietResponse response = sanPhamChiTietService.getSanPhamChiTietById(idSanPham);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/admin/variants/{idSanPham}")
    public ResponseEntity<List<SanPhamChiTietDTO>> getVariants(@PathVariable UUID idSanPham) {
        List<SanPhamChiTietDTO> variantDTOs = sanPhamChiTietService.getVariantsBySanPhamId(idSanPham);
        return ResponseEntity.ok(variantDTOs);
    }

    @GetMapping("/api/v1/product_v2")
    public ResponseEntity<?> getAllSanPhamV2(
            @RequestParam(required = false) String ma,
            @RequestParam(required = false) String ten,
            @RequestParam(required = false) UUID idChatLieu,
            @RequestParam(required = false) UUID idLopLot,
            @RequestParam(required = false) UUID idHang,
            @RequestParam(required = false) UUID idDeGiay,
            @RequestParam(required = false) UUID idDanhMuc,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ngayTao") String sortField,
            @RequestParam(defaultValue = "ASC") String sortDirection

    ) {
        Page<SanPham> result = sanPhamChiTietService.searchSanPham(
                ma, ten, idChatLieu, idLopLot, idHang, idDeGiay, idDanhMuc, page, size, sortField, sortDirection);

        return ResponseEntity.ok(result);
    }

//    //check so luowng spct
//    @GetMapping("/api/v1/admin/kiem-tra-so-luong/{id}")
//    public ResponseEntity<Map<String, Object>> checkProductQuantity(
//            @PathVariable("id") UUID productId,
//            @RequestParam("soLuong") int soLuong) {
//        boolean isAvailable = sanPhamChiTietService.checkProductQuantity(productId, soLuong);
//        String message = isAvailable ? "Số lượng sản phẩm đủ" : "Không đủ số lượng sản phẩm trong kho";
//        return ResponseEntity.ok(Map.of(
//                "isAvailable", isAvailable,
//                "message", message
//        ));
//    }

    @GetMapping("/api/v1/admin/kiem-tra-so-luong/{id}")
    public ResponseEntity<Map<String, Object>> checkProductQuantity(
            @PathVariable("id") UUID productId,
            @RequestParam("soLuong") int soLuong) {
        boolean isAvailable = sanPhamChiTietService.checkProductQuantity(productId, soLuong);
        String message = isAvailable ? "Số lượng sản phẩm đủ" : "Không đủ số lượng sản phẩm trong kho";
//        boolean isActive = sanPhamChiTietService.isProductActive(productId);
//        if (!isActive) {
//            return ResponseEntity.ok(Map.of(
//                    "isAvailable", true,
//                    "message", "Sản phẩm không ở trạng thái hoạt động"
//            ));
//        }
        return ResponseEntity.ok(Map.of(
                "isAvailable", isAvailable,
                "message", message
        ));
    }



}
