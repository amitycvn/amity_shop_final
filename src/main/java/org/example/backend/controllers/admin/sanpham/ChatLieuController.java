package org.example.backend.controllers.admin.sanpham;

import org.example.backend.common.PageResponse;
import org.example.backend.common.ResponseData;
import org.example.backend.constants.api.Admin;
import org.example.backend.dto.response.SanPham.ChatLieuRespon;
import org.example.backend.dto.response.SanPham.DeGiayRepon;
import org.example.backend.models.ChatLieu;
import org.example.backend.models.MauSac;
import org.example.backend.repositories.ChatLieuRepository;
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
public class ChatLieuController {
    @Autowired
    ChatLieuRepository chatLieuRepository;
    @GetMapping(Admin.MATERIAL_GET_ALL)
    public ResponseEntity<?> getAllChatLieu() {
        return ResponseEntity.ok(chatLieuRepository.getAll());
    }
    @PostMapping(Admin.MATERIAL_CREATE)
    public ResponseEntity<?> createChatLieu(@RequestBody ChatLieu chatLieu) {
        ChatLieu m = new ChatLieu();
        m.setMa(chatLieu.getMa());
        m.setTen(chatLieu.getTen());
        m.setTrangThai(chatLieu.getTrangThai());
        return ResponseEntity.ok(chatLieuRepository.save(m));
    }

    @PutMapping(Admin.MATERIAL_SET_UPDATE)
    public ResponseEntity<?>  updateChatLieu(@PathVariable UUID id) {
        ChatLieu m = chatLieuRepository.findById(id).orElse(null);
        if(m!=null){
            chatLieuRepository.setdeleted(!m.getDeleted(),id);
            return ResponseEntity.ok("set deleted successfully id "+id);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(Admin.MATERIAL_SEARCH)
    public ResponseEntity<?> Search(@RequestParam(value="ten" ,defaultValue = "") String ten){
        return ResponseEntity.ok(chatLieuRepository.search("%"+ten+"%"));
    }

    @GetMapping(Admin.MATERIAL_PAGE)
    public ResponseEntity<ResponseData<PageResponse<List<ChatLieuRespon>>>> phanTrang(
            @RequestParam(value="itemsPerPage",defaultValue = "5") int itemsperPage,
            @RequestParam(value = "page",defaultValue = "0") int page
    ){
        Pageable phanTrang = PageRequest.of(page, itemsperPage);
        Page<ChatLieuRespon> chatLieuPage = chatLieuRepository.phanTrang(phanTrang);

        PageResponse<List<ChatLieuRespon>> pageResponse = PageResponse.<List<ChatLieuRespon>>builder()
                .page(chatLieuPage.getNumber())
                .size(chatLieuPage.getSize())
                .totalPage(chatLieuPage.getTotalPages())
                .items(chatLieuPage.getContent())
                .build();

        ResponseData<PageResponse<List<ChatLieuRespon>>> responseData = ResponseData.<PageResponse<List<ChatLieuRespon>>>builder()
                .message("get paginated done")
                .status(HttpStatus.OK.value())
                .data(pageResponse)
                .build();

        return ResponseEntity.ok(responseData);
    }
}
