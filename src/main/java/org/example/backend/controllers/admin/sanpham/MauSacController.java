package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.dto.response.SanPham.MauSacRespon;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.MauSacRepository;
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
public class MauSacController {
    @Autowired
    MauSacRepository mauSacRepository;
    @GetMapping(Admin.COLOR_GET_ALL)
    public ResponseEntity<?> getAllMauSac() {
        return ResponseEntity.ok(mauSacRepository.getAll());
    }
    @PostMapping(Admin.COLOR_CREATE)
    public ResponseEntity<?> createMauSac(@RequestBody MauSac mauSac) {
        MauSac m = new MauSac();
        m.setMa(mauSac.getMa());
        m.setTen(mauSac.getTen());
        m.setTrangThai(mauSac.getTrangThai());
        return ResponseEntity.ok(mauSacRepository.save(m));
    }

    @PutMapping(Admin.COLOR_UPDATE)
    public ResponseEntity<?>  updateMauSac(@PathVariable UUID id) {
        MauSac m = mauSacRepository.findById(id).orElse(null);
        if(m!=null){
            mauSacRepository.setDeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(Admin.COLOR_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(mauSacRepository.search("%"+ten+"%"));
    }
    @GetMapping(Admin.COLOR_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<MauSacRespon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<MauSacRespon> mauSacPage = mauSacRepository.phanTrang(phanTrang);

        PageResponse<List<MauSacRespon>> pageResponse = PageResponse.<List<MauSacRespon>>builder()
                .page(mauSacPage.getNumber())
                .size(mauSacPage.getSize())
                .totalPage(mauSacPage.getTotalPages())
                .items(mauSacPage.getContent())
                .build();

        ResponseData<PageResponse<List<MauSacRespon>>> responseData = ResponseData.<PageResponse<List<MauSacRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
