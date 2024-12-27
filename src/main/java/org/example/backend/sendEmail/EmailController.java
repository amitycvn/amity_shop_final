package org.example.backend.sendEmail;

import lombok.RequiredArgsConstructor;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("${spring.application.api-prefix}/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final NguoiDungRepository nguoiDungRepository;

    @GetMapping("/sendNewAccountNVEmail")
    public String sendNewAccountNVEmail() {
        try {
            emailService.sendNewAccountNVEmail("thuongcm262@gmail.com", "thuongcm262@gmail.com", "a1234b5c");
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }

    @GetMapping("/sendNewAccountKHEmail")
    public String sendNewAccountKHEmail() {
        try {
            emailService.sendNewAccountKHEmail("thuongcm262@gmail.com", "thuongcm262@gmail.com", "a1234b5c");
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }

    @GetMapping("/sendPasswordEmail")
    public String sendPasswordEmail() {
        try {
            emailService.sendPasswordEmail("thuongcm262@gmail.com", "hahaha");
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }
    @GetMapping("/gui-thong-tin-khach-hang")
    public String guiThongTinKhachHang(
            @RequestParam(required = false) String email
    ) {
        try {
            emailService.sendInfomationToEmail(email);
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam(required = false) String email) {
        Optional<NguoiDung> optionalNguoiDung = nguoiDungRepository.findByEmail(email);
        if (optionalNguoiDung.isEmpty()) {
            return ResponseEntity.status(404).body("Email không tồn tại.");
        }

        NguoiDung nguoiDung = optionalNguoiDung.get();
        try {
            emailService.sendForgotPasswordEmail(email, nguoiDung);
            return ResponseEntity.ok("Email đã được gửi thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Có lỗi xảy ra khi gửi email.");
        }
    }




}
