package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.SanPhamRequest;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.dto.response.SanPham.SanPhamDetailRespon;
import org.example.backend.dto.response.SanPham.SanPhamResponse;
import org.example.backend.models.ChatLieu;
import org.example.backend.models.MauSac;
import org.example.backend.models.SanPham;
import org.example.backend.repositories.ChatLieuRepository;
import org.example.backend.repositories.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class SanPhamController {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @GetMapping(Admin.PRODUCT_GET_ALL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sanPhamRepository.getAll());
    }




    @PostMapping(Admin.PRODUCT_CREATE)
    public ResponseEntity<?> create(@RequestBody SanPhamRequest sanPhamRequest) {
        SanPham sp = new SanPham();
        sp.setMa(sanPhamRequest.getMa());
        sp.setTen(sanPhamRequest.getTen());
        sp.setIdChatLieu(sanPhamRequest.getIdChatLieu());
        sp.setIdLopLot(sanPhamRequest.getIdLopLot());
        sp.setTrangThai(sanPhamRequest.getTrangThai());
        return ResponseEntity.ok(sanPhamRepository.save(sp));
    }
    @PutMapping(Admin.PRODUCT_UPDATE)
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody SanPhamRequest sanPhamRequest) {
        SanPham sp = sanPhamRepository.findById(id).orElse(null);

        if (sp!= null){
            sp.setMa(sanPhamRequest.getMa());
            sp.setTen(sanPhamRequest.getTen());
            sp.setIdChatLieu(sanPhamRequest.getIdChatLieu());
            sp.setIdLopLot(sanPhamRequest.getIdLopLot());
            sp.setIdHang(sanPhamRequest.getIdHang());
            sp.setIdDanhMuc(sanPhamRequest.getIdDanhMuc());
            sp.setIdDeGiay(sanPhamRequest.getIdDeGiay());
            sp.setTrangThai(sanPhamRequest.getTrangThai());
            return ResponseEntity.ok(sanPhamRepository.save(sp));
        }
        SanPham sanPham = new SanPham();
        sanPham.setMa(sanPhamRequest.getMa());
        sanPham.setTen(sanPhamRequest.getTen());
        sanPham.setIdChatLieu(sanPhamRequest.getIdChatLieu());
        sanPham.setIdLopLot(sanPhamRequest.getIdLopLot());
        sanPham.setIdHang(sanPhamRequest.getIdHang());
        sanPham.setIdDanhMuc(sanPhamRequest.getIdDanhMuc());
        sanPham.setIdDeGiay(sanPhamRequest.getIdDeGiay());
        sanPham.setTrangThai(sanPhamRequest.getTrangThai());
        return ResponseEntity.ok(sanPhamRepository.save(sanPham));
    }


    @PutMapping(Admin.PRODUCT_SET_DELETE)
    public ResponseEntity<?>  updateSanPham(@PathVariable UUID id) {
        SanPham m = sanPhamRepository.findById(id).orElse(null);
        if(m!=null){
            sanPhamRepository.setDeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping(Admin.PRODUCT_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(sanPhamRepository.search("%"+ten+"%"));
    }
    @GetMapping(Admin.PRODUCT_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<SanPhamResponse>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<SanPhamResponse> sanPhamPage = sanPhamRepository.phanTrang(phanTrang);

        PageResponse<List<SanPhamResponse>> pageResponse = PageResponse.<List<SanPhamResponse>>builder()
                .page(sanPhamPage.getNumber())
                .size(sanPhamPage.getSize())
                .totalPage(sanPhamPage.getTotalPages())
                .items(sanPhamPage.getContent())
                .build();

        ResponseData<PageResponse<List<SanPhamResponse>>> responseData = ResponseData.<PageResponse<List<SanPhamResponse>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }



}
