//package org.example.backend.controllers.admin.sanpham;
//
//import org.example.backend.constants.api.Admin;
//import org.example.backend.models.HinhAnh;
//import org.example.backend.repositories.HinhAnhRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//public class HinhAnhController {
//    @Autowired
//    HinhAnhRepository hinhAnhRepository;
//
//    @GetMapping(Admin.IMAGE_GET_ALL)
//    public ResponseEntity<?> getAllHinhAnh() {
//        return ResponseEntity.ok(hinhAnhRepository.getAll());
//    }
//    @PostMapping(Admin.IMAGE_CREATE)
//    public ResponseEntity<?> createHinhAnh(@RequestBody HinhAnh hinhAnh) {
//        HinhAnh m = new HinhAnh();
//        m.setMa(hinhAnh.getMa());
//        m.setTen(hinhAnh.getTen());
//        m.setUrl(hinhAnh.getUrl());
//        m.setTrangThai(hinhAnh.getTrangThai());
//        return ResponseEntity.ok(hinhAnhRepository.save(m));
//    }
//
//    @PutMapping(Admin.IMAGE_SET_UPDATE)
//    public ResponseEntity<?>  updateHinhAnh(@PathVariable UUID id) {
//        HinhAnh m = hinhAnhRepository.findById(id).orElse(null);
//        if(m!=null){
//            hinhAnhRepository.setDeleted(!m.getDeleted(),id);
//            return ResponseEntity.ok("set deleted successfully id "+id);
//        }
//        return ResponseEntity.notFound().build();
//    }
//}

package org.example.backend.controllers.admin.sanpham;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.example.backend.dto.request.sanPham.HinhAnhRequest;
import org.example.backend.models.HinhAnh;
import org.example.backend.repositories.HinhAnhRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController

public class HinhAnhController {



    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @PostMapping("/upload")


    public ResponseEntity<String> uploadImage(
//            @RequestBody HinhAnhRequest request,
            @RequestParam("file") MultipartFile file) {
        try {

            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");


            HinhAnh hinhAnh = new HinhAnh();
            hinhAnh.setUrl(imageUrl);
            hinhAnh.setMa("a");
            hinhAnh.setTen("a");
            hinhAnh.setIdSpct(null);


            
            hinhAnhRepository.save(hinhAnh);

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Tải lên hình ảnh thất bại: " + e.getMessage());
        }
    }
}

