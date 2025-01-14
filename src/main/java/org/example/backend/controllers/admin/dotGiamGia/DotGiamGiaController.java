package org.example.backend.controllers.admin.dotGiamGia;

import jakarta.validation.Valid;
import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.PaginationConstants;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaCreate;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaUpdate;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.mapper.dotGiamGia.DotGiamGiaMapper;
import org.example.backend.models.DotGiamGia;
import org.example.backend.repositories.DotGiamGiaRepository;
import org.example.backend.services.DotGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


import static org.example.backend.constants.Status.HOAT_DONG;
import static org.example.backend.constants.Status.SAP_DIEN_RA;
import static org.example.backend.constants.api.Admin.*;

import static org.example.backend.constants.api.Admin.SALE_CREATE;
import static org.example.backend.constants.api.Admin.SALE_DELETE;
import static org.example.backend.constants.api.Admin.SALE_GET_ALL;
import static org.example.backend.constants.api.Admin.SALE_GET_BY_ID;
import static org.example.backend.constants.api.Admin.SALE_PRODUCT_DETAIL_GET_ALL;
import static org.example.backend.constants.api.Admin.SALE_SEARCH_VALUE;
import static org.example.backend.constants.api.Admin.SALE_SET_DELETE;
import static org.example.backend.constants.api.Admin.SALE_UPDATE;


@RestController
public class DotGiamGiaController {
    final DotGiamGiaService dotGiamGiaService;

    public DotGiamGiaController(DotGiamGiaService dotGiamGiaService, DotGiamGiaMapper dotGiamGiaMapper) {
        this.dotGiamGiaService = dotGiamGiaService;
        this.dotGiamGiaMapper = dotGiamGiaMapper;
    }

    final DotGiamGiaMapper dotGiamGiaMapper;
    @Autowired
    private DotGiamGiaRepository dotGiamGiaRepository;

    @GetMapping(SALE_GET_ALL)
    public ResponseEntity<?> getAllSalePaginate(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "ngayTao") String sortBy,
                                                @RequestParam(defaultValue = "desc") String sortDir) {
        PageResponse<List<DotGiamGiaResponse>> dotGiamGiaPage = dotGiamGiaService.dotGiamGiaGetAllPaginate(page, size, sortBy, sortDir);
        ResponseData<PageResponse<List<DotGiamGiaResponse>>> responseData = ResponseData.<PageResponse<List<DotGiamGiaResponse>>>builder()
                .message("Get all sale paginate")
                .status(HttpStatus.OK.value())
                .data(dotGiamGiaPage).build();
        return ResponseEntity.ok().body(responseData);
    }
    //dot gg ben client

    @GetMapping(SALE_GET_ALL_CLIENT)
    public ResponseEntity<?> getAllClientPaginate(){
        List<String> trangThais = List.of(HOAT_DONG,SAP_DIEN_RA);
        return ResponseEntity.ok(dotGiamGiaRepository.getAllDotGiamGiaClient(trangThais));
    }
    @GetMapping(SALE_SEARCH_VALUE)
    public ResponseEntity<?> searchSalePaginate(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "ngayTao") String sortBy,
                                                @RequestParam(defaultValue = "desc") String sortDir,
                                                @RequestParam(defaultValue = "") String value,
                                                @RequestParam(defaultValue = "") Instant minNgay,
                                                @RequestParam(defaultValue = "") Instant maxNgay,
                                                @RequestParam(defaultValue = "") String trangThai,
                                                @RequestParam(defaultValue = "") BigDecimal minGia,
                                                @RequestParam(defaultValue = "") BigDecimal maxGia
                                                ){
        DotGiamGiaSearch dotGiamGiaSearch = new DotGiamGiaSearch();
        dotGiamGiaSearch.setMaxGia(maxGia);
        dotGiamGiaSearch.setMinGia(minGia);
        dotGiamGiaSearch.setTrangThai(trangThai);
        dotGiamGiaSearch.setMinNgay(minNgay);
        dotGiamGiaSearch.setMaxNgay(maxNgay);
        dotGiamGiaSearch.setValue(value);
        PageResponse<List<DotGiamGiaResponse>> searchDggPage = dotGiamGiaService.searchDotGiamGia(page, size, sortBy, sortDir, dotGiamGiaSearch);
        ResponseData<PageResponse<List<DotGiamGiaResponse>>> responseData = ResponseData.<PageResponse<List<DotGiamGiaResponse>>>builder()
                .message("Search Sale")
                .status(HttpStatus.OK.value())
                .data(searchDggPage).build();
        return ResponseEntity.ok().body(responseData);
    }

    @PostMapping(SALE_CREATE)
    public ResponseEntity<?> createSale(@RequestBody DotGiamGiaCreate dotGiamGiaCreate) {
        try {
            // Kiểm tra mã
//            if (dotGiamGiaCreate.getMa() == null || dotGiamGiaCreate.getMa().isBlank() ||
//                dotGiamGiaCreate.getMa().length() < 5 || dotGiamGiaCreate.getMa().length() > 10) {
//                return ResponseEntity.badRequest().body("Mã phải có độ dài từ 5 đến 10 ký tự");
//            }

            // Kiểm tra tên
            if (dotGiamGiaCreate.getTen() == null || dotGiamGiaCreate.getTen().isBlank() ||
                dotGiamGiaCreate.getTen().length() < 5 || dotGiamGiaCreate.getTen().length() > 20) {
                return ResponseEntity.badRequest().body("Tên phải có độ dài từ 5 đến 20 ký tự");
            }

            // Kiểm tra giá trị
//            if (dotGiamGiaCreate.getGiaTri() == null || dotGiamGiaCreate.getGiaTri().compareTo(BigDecimal.ZERO) <= 0 ||
//                dotGiamGiaCreate.getGiaTri().compareTo(new BigDecimal("999999999")) > 0) {
//                return ResponseEntity.badRequest().body("Giá trị tiền mặt phải lớn hơn 10,000 và nhỏ hơn 999,999,999");
//            }

            if (dotGiamGiaCreate.getLoai() != null) {
                if (dotGiamGiaCreate.getLoai()) {
                    // Tiền mặt phải lớn hơn 10,000
                    if (dotGiamGiaCreate.getGiaTri().compareTo(new BigDecimal("10000")) < 0 || dotGiamGiaCreate.getGiaTri().compareTo(new BigDecimal("999999999")) > 0) {
                        return ResponseEntity.badRequest().body("Giá trị tiền mặt phải từ 10,000 đến 999.999.999");
                    }
                } else {
                    // Phần trăm phải từ 10 đến 80
                    if (dotGiamGiaCreate.getGiaTri().compareTo(new BigDecimal("10")) < 0 ||
                        dotGiamGiaCreate.getGiaTri().compareTo(new BigDecimal("80")) > 0) {
                        return ResponseEntity.badRequest().body("Phần trăm giảm giá phải từ 10 đến 80");
                    }
                }
            } else {
                return ResponseEntity.badRequest().body("Loại không được để trống");
            }

            // Kiểm tra hình thức
            if (dotGiamGiaCreate.getHinhThuc() == null || dotGiamGiaCreate.getHinhThuc().isBlank()) {
                return ResponseEntity.badRequest().body("Hình thức không được để trống");
            }


            // Kiểm tra điều kiện
            if ("Theo sản phẩm".equals(dotGiamGiaCreate.getHinhThuc())) {
                dotGiamGiaCreate.setDieuKien(-1);
            } else if ("Theo hóa đơn".equals(dotGiamGiaCreate.getHinhThuc())) {
                if (dotGiamGiaCreate.getDieuKien() == null ||
                    (dotGiamGiaCreate.getDieuKien() != -1 &&
                     (dotGiamGiaCreate.getDieuKien() < 10000 || dotGiamGiaCreate.getDieuKien() > 100000000))) {
                    return ResponseEntity.badRequest().body("Điều kiện phải lớn hơn 10,000, nhỏ hơn 100,000,000 hoặc bằng -1");
                }
            } else {
                return ResponseEntity.badRequest().body("Hình thức không hợp lệ");
            }

            // Kiểm tra ngày bắt đầu
            if (dotGiamGiaCreate.getNgayBatDau() == null) {
                return ResponseEntity.badRequest().body("Ngày bắt đầu không được để trống");
            }
            if (dotGiamGiaCreate.getNgayBatDau().isBefore(Instant.now())) {
                return ResponseEntity.badRequest().body("Ngày bắt đầu phải lớn hơn thời điểm hiện tại");
            }

            // Kiểm tra ngày kết thúc
            if (dotGiamGiaCreate.getNgayKetThuc() == null) {
                return ResponseEntity.badRequest().body("Ngày kết thúc không được để trống");
            }
            if (dotGiamGiaCreate.getNgayKetThuc().isBefore(dotGiamGiaCreate.getNgayBatDau())) {
                return ResponseEntity.badRequest().body("Ngày kết thúc không được nhỏ hơn ngày bắt đầu");
            }

            // Kiểm tra trạng thái
            if (dotGiamGiaCreate.getTrangThai() == null || dotGiamGiaCreate.getTrangThai().isBlank()) {
                dotGiamGiaCreate.setTrangThai("Sắp diễn ra");
            }



            // Nếu tất cả kiểm tra đều hợp lệ, tiến hành lưu
            DotGiamGia d = new DotGiamGia();
            dotGiamGiaMapper.createDotGiamGiaFromDto(dotGiamGiaCreate, d);
            d.setTrangThai("Sắp diễn ra");
            return ResponseEntity.ok().body(dotGiamGiaService.save(d));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }




    @PutMapping(SALE_UPDATE)
    public ResponseEntity<?> updateSale(
            @PathVariable UUID id,
            @RequestBody DotGiamGiaUpdate dotGiamGiaUpdate) {
        try {
            DotGiamGia existingDotGiamGia = dotGiamGiaService.findById(id).orElseThrow(()-> new RuntimeException("Đợt giảm giá không tồn tại"));
            if (existingDotGiamGia==null) {
                return ResponseEntity.notFound().build();
            }

            // Kiểm tra mã
//            if (dotGiamGiaUpdate.getMa() == null || dotGiamGiaUpdate.getMa().isBlank() ||
//                dotGiamGiaUpdate.getMa().length() < 5 || dotGiamGiaUpdate.getMa().length() > 10) {
//                return ResponseEntity.badRequest().body("Mã phải có độ dài từ 5 đến 10 ký tự");
//            }

            // Kiểm tra tên
            if (dotGiamGiaUpdate.getTen() == null || dotGiamGiaUpdate.getTen().isBlank() ||
                dotGiamGiaUpdate.getTen().length() < 5 || dotGiamGiaUpdate.getTen().length() > 20) {
                return ResponseEntity.badRequest().body("Tên phải có độ dài từ 5 đến 20 ký tự");
            }



            if (dotGiamGiaUpdate.getLoai() != null) {
                if (dotGiamGiaUpdate.getLoai()) {
                    // Tiền mặt phải lớn hơn 10,000
                    if (dotGiamGiaUpdate.getGiaTri().compareTo(new BigDecimal("10000")) < 0 || dotGiamGiaUpdate.getGiaTri().compareTo(new BigDecimal("999999999")) > 0) {
                        return ResponseEntity.badRequest().body("Giá trị tiền mặt phải từ 10,000 đến 999.999.999");
                    }
                } else {
                    // Phần trăm phải từ 10 đến 80
                    if (dotGiamGiaUpdate.getGiaTri().compareTo(new BigDecimal("10")) < 0 ||
                        dotGiamGiaUpdate.getGiaTri().compareTo(new BigDecimal("80")) > 0) {
                        return ResponseEntity.badRequest().body("Phần trăm giảm giá phải từ 10 đến 80");
                    }
                }
            } else {
                return ResponseEntity.badRequest().body("Loại không được để trống");
            }

            // Kiểm tra giá trị
            if (dotGiamGiaUpdate.getGiaTri() == null || dotGiamGiaUpdate.getGiaTri().compareTo(BigDecimal.ZERO) <= 0 ||
                dotGiamGiaUpdate.getGiaTri().compareTo(new BigDecimal("999999999")) > 0) {
                return ResponseEntity.badRequest().body("Giá trị tiền mặt phải lớn hơn 10,000 và nhỏ hơn 999,999,999");
            }

            // Kiểm tra hình thức
            if (dotGiamGiaUpdate.getHinhThuc() == null || dotGiamGiaUpdate.getHinhThuc().isBlank()) {
                return ResponseEntity.badRequest().body("Hình thức không được để trống");
            }

            // Kiểm tra điều kiện
            if ("Theo sản phẩm".equals(dotGiamGiaUpdate.getHinhThuc())) {
                dotGiamGiaUpdate.setDieuKien(-1);
            } else if ("Theo hóa đơn".equals(dotGiamGiaUpdate.getHinhThuc())) {
                if (dotGiamGiaUpdate.getDieuKien() == null ||
                    (dotGiamGiaUpdate.getDieuKien() != -1 &&
                     (dotGiamGiaUpdate.getDieuKien() < 10000 || dotGiamGiaUpdate.getDieuKien() > 100000000))) {
                    return ResponseEntity.badRequest().body("Điều kiện phải lớn hơn 10,000, nhỏ hơn 100,000,000 hoặc bằng -1");
                }
            } else {
                return ResponseEntity.badRequest().body("Hình thức không hợp lệ");
            }

            // Kiểm tra ngày bắt đầu
            if (dotGiamGiaUpdate.getNgayBatDau() == null) {
                return ResponseEntity.badRequest().body("Ngày bắt đầu không được để trống");
            }
            if (!existingDotGiamGia.getNgayBatDau().equals(dotGiamGiaUpdate.getNgayBatDau())) {
                if (dotGiamGiaUpdate.getNgayBatDau().isBefore(Instant.now())) {
                    return ResponseEntity.badRequest().body("Ngày bắt đầu phải lớn hơn thời điểm hiện tại");
                }
            }

            // Kiểm tra ngày kết thúc
            if (dotGiamGiaUpdate.getNgayKetThuc() == null) {
                return ResponseEntity.badRequest().body("Ngày kết thúc không được để trống");
            }
            if (dotGiamGiaUpdate.getNgayKetThuc().isBefore(dotGiamGiaUpdate.getNgayBatDau())) {
                return ResponseEntity.badRequest().body("Ngày kết thúc không được nhỏ hơn ngày bắt đầu");
            }

            // Kiểm tra trạng thái
            if (dotGiamGiaUpdate.getTrangThai() == null || dotGiamGiaUpdate.getTrangThai().isBlank()) {
                dotGiamGiaUpdate.setTrangThai("Sắp diễn ra");
            }



            // Cập nhật dữ liệu
            DotGiamGia dotGiamGia = existingDotGiamGia;
            dotGiamGiaMapper.updateDotGiamGiaFromDto(dotGiamGiaUpdate, dotGiamGia);
            return ResponseEntity.ok(dotGiamGiaService.save(dotGiamGia));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }


    @DeleteMapping(SALE_DELETE)
    public ResponseEntity<?> deleteSale(@PathVariable UUID id) {
        Optional<DotGiamGia> dotGiamGia = dotGiamGiaService.findById(id);
        if (dotGiamGia.isPresent()) {
            dotGiamGiaService.deleteById(id);
            return ResponseEntity.ok().body("Delete id: " + id);
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping(SALE_GET_BY_ID)
    public ResponseEntity<?> getSaleById(@PathVariable UUID id) {
        DotGiamGia dotGiamGia = dotGiamGiaService.findById(id).orElse(null);
        if (dotGiamGia != null) {
            DotGiamGiaResponse dotGiamGiaResponse = new DotGiamGiaResponse();
            dotGiamGiaMapper.getDtoFromDotGiamGia(dotGiamGiaResponse, dotGiamGia);
            return ResponseEntity.ok().body(dotGiamGiaResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(SALE_SET_DELETE)
    public ResponseEntity<?> setSaleDelete(@PathVariable UUID id) {
        DotGiamGia d = dotGiamGiaService.findById(id).orElse(null);
        if (d != null) {
            dotGiamGiaService.setDeletedDotGiamGia(!d.getDeleted(), id);
            return ResponseEntity.ok().body("Set deleted id: " + id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(SALE_PRODUCT_DETAIL_GET_ALL)
    public ResponseEntity<?> getAllDotGiamGiaSpct() {
        return ResponseEntity.ok().body(dotGiamGiaService.getAllDotGiamGiaSpct());
    }

}
