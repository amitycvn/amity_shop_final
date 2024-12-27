package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.dto.response.SanPham.KichThuocRespon;
import org.example.backend.models.KichThuoc;
import org.example.backend.repositories.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class KichThuocController {
    @Autowired
    KichThuocRepository kichThuocRepository;
    @GetMapping(Admin.SIZE_GET_ALL)
    public ResponseEntity<?> getAllKichThuoc() {
       return ResponseEntity.ok(kichThuocRepository.getAllKichThuoc());
    }
    @PostMapping(Admin.SIZE_CREATE)
    public ResponseEntity<?> createKichThuoc(@RequestBody KichThuoc kichThuoc) {
        KichThuoc kt = new KichThuoc();
        kt.setMa(kichThuoc.getMa());
        kt.setTen(kichThuoc.getTen());
        kt.setTrangThai(kichThuoc.getTrangThai());
        return ResponseEntity.ok(kichThuocRepository.save(kt));
    }
    @PutMapping(Admin.SIZE_UPDATE)
    public ResponseEntity<?> update (@PathVariable UUID id) {
        KichThuoc kt = kichThuocRepository.findById(id).orElse(null);
        if (kt != null) {
            kichThuocRepository.deletedKichThuoc(!kt.getDeleted(),id);
            return ResponseEntity.ok("set deleted id " +id );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.SIZE_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(kichThuocRepository.search("%"+ten+"%"));
    }

    @GetMapping(Admin.SIZE_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<KichThuocRespon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<KichThuocRespon> kichThuocPage = kichThuocRepository.phanTrang(phanTrang);

        PageResponse<List<KichThuocRespon>> pageResponse = PageResponse.<List<KichThuocRespon>>builder()
                .page(kichThuocPage.getNumber())
                .size(kichThuocPage.getSize())
                .totalPage(kichThuocPage.getTotalPages())
                .items(kichThuocPage.getContent())
                .build();

        ResponseData<PageResponse<List<KichThuocRespon>>> responseData = ResponseData.<PageResponse<List<KichThuocRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }

}
