package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.models.DeGiay;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.DeGiayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.DoubleStream.builder;

@RestController
public class DeGiayController {
    @Autowired
    DeGiayRepository deGiayRepository;
    @GetMapping(Admin.SOLE_GET_ALL)
    public ResponseEntity<?> getAllDeGiay() {
        return ResponseEntity.ok(deGiayRepository.getAll());
    }
    @PostMapping(Admin.SOLE_CREATE)
    public ResponseEntity<?> createMauSac(@RequestBody DeGiay deGiay) {
        DeGiay d = new DeGiay();
        d.setMa(deGiay.getMa());
        d.setTen(deGiay.getTen());
        d.setTrangThai(deGiay.getTrangThai());
        return ResponseEntity.ok(deGiayRepository.save(d));
    }

    @PutMapping(Admin.SOLE_SET_UPDATE)
    public ResponseEntity<?>  updateDeGiay(@PathVariable UUID id) {
        DeGiay m = deGiayRepository.findById(id).orElse(null);
        if(m!=null){
            deGiayRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(Admin.SOLE_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(deGiayRepository.search("%"+ten+"%"));
    }
    @GetMapping(Admin.SOLE_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<DeGiayRepon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<DeGiayRepon> deGiayPage = deGiayRepository.phanTrang(phanTrang);

        PageResponse<List<DeGiayRepon>> pageResponse = PageResponse.<List<DeGiayRepon>>builder()
                .page(deGiayPage.getNumber())
                .size(deGiayPage.getSize())
                .totalPage(deGiayPage.getTotalPages())
                .items(deGiayPage.getContent())
                .build();

        ResponseData<PageResponse<List<DeGiayRepon>>> responseData = ResponseData.<PageResponse<List<DeGiayRepon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
