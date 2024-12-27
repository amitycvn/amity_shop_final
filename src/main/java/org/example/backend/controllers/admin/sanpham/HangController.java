package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.HangRequest;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.dto.response.SanPham.HangRespon;
import org.example.backend.models.Hang;
import org.example.backend.repositories.HangRepository;
import org.hibernate.annotations.Parameter;
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
public class HangController {
    final
    HangRepository HangRepository;
    private final HangRepository hangRepository;

    public HangController(HangRepository HangRepository, HangRepository hangRepository) {
        this.HangRepository = HangRepository;
        this.hangRepository = hangRepository;
    }
    @GetMapping(Admin.COMPANY_GET_ALL)
    public ResponseEntity<?> getAll() {
       return ResponseEntity.ok(HangRepository.getAll());
    }
    @PostMapping(Admin.COMPANY_CREATE)
    public ResponseEntity<?> create(@RequestBody HangRequest hang) {
        Hang h = new Hang();
        h.setMa(hang.getMa());
        h.setTen(hang.getTen());
        h.setTrangThai(hang.getTrangThai());
        return ResponseEntity.ok(HangRepository.save(h));
    }
    @PutMapping(Admin.COMPANY_UPDATE)
    public ResponseEntity<?> update(@PathVariable UUID id) {
        Hang h = hangRepository.findById(id).orElse(null);
        if (h != null) {
            hangRepository.deletedHang(!h.getDeleted(),id);
            return ResponseEntity.ok("Set deleted id: "+id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.COMPANY_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(hangRepository.search("%"+ten+"%"));
    }

    @GetMapping(Admin.COMPANY_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<HangRespon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<HangRespon> hangPage = hangRepository.phanTrang(phanTrang);

        PageResponse<List<HangRespon>> pageResponse = PageResponse.<List<HangRespon>>builder()
                .page(hangPage.getNumber())
                .size(hangPage.getSize())
                .totalPage(hangPage.getTotalPages())
                .items(hangPage.getContent())
                .build();

        ResponseData<PageResponse<List<HangRespon>>> responseData = ResponseData.<PageResponse<List<HangRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }

}
