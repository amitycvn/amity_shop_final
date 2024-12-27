package org.example.backend.sendEmail;

import jakarta.transaction.Transactional;
import org.example.backend.models.NguoiDung;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.NguoiDungService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final NguoiDungRepository nguoiDungRepository;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public EmailService(JavaMailSender javaMailSender,PasswordEncoder passwordEncoder,NguoiDungRepository nguoiDungRepository) {
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = passwordEncoder;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    public void sendNewAccountNVEmail(String recipientEmail, String email ,String matKhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Chào Mừng: Thư xác nhận nhân viên của");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                        "Bạn vừa dùng mail này để đăng kí tài khoản http://localhost:3000,\n\n" +
                        "Tài khoản mới với tên đăng nhập : "+ email+" ,\n\n" +
                        "Mật Khẩu đăng nhập : " + matKhau + " ,\n\n" +
                        "Một lần nữa chúc mừng bạn là thành viên của http://localhost:3000 : http://localhost:3000  ,\n\n" +
                        " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                        "Trân trọng,\n[]");

        javaMailSender.send(message);
    }
    public void sendNewAccountKHEmail(String recipientEmail, String email ,String matkhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Thông báo: Tài khoản mới đã được tạo");
        message.setText("Chào '"+ recipientEmail +"' ,\n\n" +
                        "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
                        "Tài khoản mới với tên đăng nhập : "+ email +" ,\n\n" +
                        "Mật Khẩu đăng nhập : " +matkhau  + " ,\n\n" +
                        "Cùng đăng nhập để trải nhiệm nhiều tiện ích tuyệt vời nhé :  http://localhost:3000  ,\n\n" +
                        " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                        "Trân trọng,\n[]");

        javaMailSender.send(message);
    }



    public void sendPasswordEmail(String recipientEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("THÔNG BÁO : BẠN ĐÃ YÊU CẦU KHÔI PHỤC MẬT KHẨU !");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                        "Bạn vừa dùng mail này để xác nhận quên mật khẩu tài khoản BeeStore,\n\n" +
                        "Mật Khẩu đăng nhập mới của bạn là : " + newPassword + " ,\n\n" +
                        "Nếu bạn không xác nhận quên mật khẩu mà vẫn nhận được mail này thì hãy liên hệ lại với http://localhost:3000 ngay lập tức qua hotline :   ,\n\n" +
                        "Trân trọng,\n[]");

        javaMailSender.send(message);
    }

    public void sendInfomationToEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("THÔNG BÁO : THÔNG TIN GIỚI THIỆU AMITY SHOP !");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                "Bạn vừa dùng mail này đăng ký nhận các thông tin từ Amity Shop,\n\n" +
                "Mới bạn đăng ký tài khoản tại http://localhost:3000/resgiter để có thẻ nhận dược nhiều ưu đã đang diễn ra :   ,\n\n" +
                "Chúng tôi xin gửi tặng bạn mã giảm giá đầu tiên ABCXYZ, click vào http://localhost:3000 để có thể sử dụng ngay  ,\n\n" +
                "Trân trọng,\n[]");

        javaMailSender.send(message);
    }
    private String generateRandomPassword(int length) {
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(charset.charAt(random.nextInt(charset.length())));
        }
        return password.toString();
    }

    @Transactional  // Ensures that the database changes are committed
    public String sendForgotPasswordEmail(String recipientEmail, NguoiDung nguoiDung) {
        String randomPassword = generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(randomPassword);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("THÔNG BÁO : BẠN ĐÃ YÊU CẦU KHÔI PHỤC MẬT KHẨU !");
        message.setText("Chào " + recipientEmail + " ,\n\n" +
                "Bạn vừa dùng mail này để xác nhận quên mật khẩu tài khoản BeeStore,\n\n" +
                "Mật khẩu mới của bạn là: " + randomPassword + "\n\n" +
                "Nếu bạn không xác nhận quên mật khẩu mà vẫn nhận được mail này thì hãy liên hệ lại với http://localhost:3000 ngay lập tức qua hotline: ,\n\n" +
                "Trân trọng,\nBeeStore");

        javaMailSender.send(message);
        nguoiDung.setMatKhau(encodedPassword);
        nguoiDungRepository.save(nguoiDung);
        return "Email đã được gửi. Vui lòng kiểm tra hộp thư của bạn để lấy mật khẩu mới.";
    }



}
