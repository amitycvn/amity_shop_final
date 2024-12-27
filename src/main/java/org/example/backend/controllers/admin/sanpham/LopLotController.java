package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.request.sanPham.LopLotRequest;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.dto.response.SanPham.LopLotRepon;
import org.example.backend.models.LopLot;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.LopLotRepository;
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
public class LopLotController {
    @Autowired
    LopLotRepository lopLotRepository;
    @GetMapping(Admin.LINING_GET_ALL)
    public ResponseEntity<?> getAllLoplot() {
        return ResponseEntity.ok(lopLotRepository.getAll());
    }
    @PostMapping(Admin.LINING_CREATE)
    public ResponseEntity<?> createLOplot(@RequestBody LopLotRequest lopLotRequest) {
        LopLot m = new LopLot();
        m.setMa(lopLotRequest.getMa());
        m.setTen(lopLotRequest.getTen());
        m.setTrangThai(lopLotRequest.getTrangThai());
        return ResponseEntity.ok(lopLotRepository.save(m));
    }

    @PutMapping(Admin.LINING_SET_UPDATE)
    public ResponseEntity<?>  updateLopLot(@PathVariable UUID id) {
        LopLot m = lopLotRepository.findById(id).orElse(null);
        if(m!=null){
            lopLotRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.LINING_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(lopLotRepository.search("%"+ten+"%"));
    }

    @GetMapping(Admin.LINING_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<LopLotRepon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<LopLotRepon> lopLotPage = lopLotRepository.phanTrang(phanTrang);

        PageResponse<List<LopLotRepon>> pageResponse = PageResponse.<List<LopLotRepon>>builder()
                .page(lopLotPage.getNumber())
                .size(lopLotPage.getSize())
                .totalPage(lopLotPage.getTotalPages())
                .items(lopLotPage.getContent())
                .build();

        ResponseData<PageResponse<List<LopLotRepon>>> responseData = ResponseData.<PageResponse<List<LopLotRepon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
