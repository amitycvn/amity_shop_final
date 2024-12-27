package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.SanPham.DanhMucRespon;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.models.DanhMuc;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class DanhMucController {
    @Autowired
    DanhMucRepository danhMucRepository;
    @GetMapping(Admin.LIST_GET_ALL)
    public ResponseEntity<?> getAllMauSac() {
        return ResponseEntity.ok(danhMucRepository.getAll());
    }
    @PostMapping(Admin.LIST_CREATE)
    public ResponseEntity<?> createMauSac(@RequestBody DanhMuc danhMuc) {
        DanhMuc m = new DanhMuc();
        m.setMa(danhMuc.getMa());
        m.setTen(danhMuc.getTen());
        m.setTrangThai(danhMuc.getTrangThai());
        return ResponseEntity.ok(danhMucRepository.save(m));
    }

    @PutMapping(Admin.LIST_SET_UPDATE)
    public ResponseEntity<?>  updateMauSac(@PathVariable UUID id) {
        DanhMuc m = danhMucRepository.findById(id).orElse(null);
        if(m!=null){
            danhMucRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.LIST_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(danhMucRepository.search("%"+ten+"%"));
    }

    @GetMapping(Admin.LIST_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<DanhMucRespon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<DanhMucRespon> danhMucPage = danhMucRepository.phanTrang(phanTrang);

        PageResponse<List<DanhMucRespon>> pageResponse = PageResponse.<List<DanhMucRespon>>builder()
                .page(danhMucPage.getNumber())
                .size(danhMucPage.getSize())
                .totalPage(danhMucPage.getTotalPages())
                .items(danhMucPage.getContent())
                .build();

        ResponseData<PageResponse<List<DanhMucRespon>>> responseData = ResponseData.<PageResponse<List<DanhMucRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
