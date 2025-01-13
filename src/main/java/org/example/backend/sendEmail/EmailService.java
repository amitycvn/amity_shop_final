package org.example.backend.sendEmail;

import jakarta.transaction.Transactional;
import org.example.backend.models.NguoiDung;
import org.example.backend.models.PhieuGiamGia;
import org.example.backend.repositories.NguoiDungRepository;
import org.example.backend.services.NguoiDungService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
                        "Bạn vừa dùng mail này để đăng kí tài khoản AmityShop,\n\n" +
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
                        "Bạn vừa dùng mail này để xác nhận quên mật khẩu tài khoản AmityShop,\n\n" +
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
                "Bạn vừa dùng mail này để xác nhận quên mật khẩu tài khoản AmityShop,\n\n" +
                "Mật khẩu mới của bạn là: " + randomPassword + "\n\n" +
                "Nếu bạn không xác nhận quên mật khẩu mà vẫn nhận được mail này thì hãy liên hệ lại với http://localhost:3000 ngay lập tức qua hotline: ,\n\n" +
                "Trân trọng,\nAmityShop");

        javaMailSender.send(message);
        nguoiDung.setMatKhau(encodedPassword);
        nguoiDungRepository.save(nguoiDung);
        return "Email đã được gửi. Vui lòng kiểm tra hộp thư của bạn để lấy mật khẩu mới.";
    }

    @Transactional
    public String sendDiscountNotificationEmail(String recipientEmail, String maGG, BigDecimal giaTri, Boolean loai, Instant ngayBatDau, Instant ngayKetThuc, NguoiDung nguoiDung) {
        // Kiểm tra nếu email của người dùng trùng khớp với email nhận
        if (!nguoiDung.getEmail().equals(recipientEmail)) {
            throw new IllegalArgumentException("Không tìm thấy người dùng với email được cung cấp.");
        }

        // Ép kiểu từ BigDecimal về int
        String giaTriV2;
        int giaTriInt = giaTri.intValue();
        if (loai == false) {
            giaTriV2 = giaTriInt + " vnd";
        } else {
            giaTriV2 = giaTriInt + " %";
        }

        // Định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String ngayBatDauFormatted = formatter.format(ngayBatDau.atZone(ZoneId.systemDefault()));
        String ngayKetThucFormatted = formatter.format(ngayKetThuc.atZone(ZoneId.systemDefault()));

        // Tạo nội dung email với hình ảnh
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");

            message.setFrom(senderEmail); // Địa chỉ email người gửi
            message.setTo(recipientEmail); // Địa chỉ email người nhận
            message.setSubject("THÔNG BÁO: ƯU ĐÃI ĐẶC BIỆT DÀNH CHO BẠN!");

            // Nội dung email với HTML format và nhúng hình ảnh
            String htmlContent = "<html><body>" +
                    "<p >Chào <strong>" + nguoiDung.getTen() + "</strong>,</p>" +
                    "<p>Chúng tôi rất vui thông báo rằng bạn đã nhận được mã giảm giá đặc biệt từ AmityShop!</p>" +
                    "<p>Thông tin chi tiết về mã giảm giá của bạn:</p>" +
                    "<ul>" +
                    "<li style='font-size: 18px;'><strong>Mã giảm giá:</strong> " + maGG + "</li>" +
                    "<li style='font-size: 18px;'><strong>Giá trị được giảm:</strong> " + giaTriV2 + "</li>" +
                    "<li style='font-size: 18px;'><strong>Bắt đầu từ:</strong> " + ngayBatDauFormatted + " <strong>tới ngày:</strong> " + ngayKetThucFormatted + "</li>" +
                    "</ul>" +
                    "<p>Hãy sử dụng mã giảm giá này khi mua sắm tại AmityShop để nhận ưu đãi hấp dẫn.</p>" +
                    "<p>Cảm ơn bạn đã tin tưởng và ủng hộ AmityShop.</p>" +
                    "<p>Trân trọng,<br>AmityShop</p>" +
                    "<p><a href='http://localhost:3000/' target='_blank'>Truy cập website AmityShop tại đây</a></p>" + // Đã thêm đường dẫn ở đây
                    "<p>Hotline: 1900-1234</p>" +
                    "<img src='cid:voucher-image' alt='Voucher Image'>" +  // Nhúng hình ảnh vào nội dung
                    "</body></html>";

            message.setText(htmlContent, true); // Gửi dưới dạng HTML

            // Sử dụng UrlResource thay vì FileSystemResource để đính kèm hình ảnh từ URL
            String imageUrl = "https://res.cloudinary.com/dlwrhv088/image/upload/v1736751048/lwby4qez4rlt5m6i6h4p.jpg";
            UrlResource resource = new UrlResource(imageUrl);
            message.addInline("voucher-image", resource); // Thêm hình ảnh vào email
        };

        // Gửi email
        javaMailSender.send(preparator);

        return "Email thông báo mã giảm giá đã được gửi thành công. Vui lòng kiểm tra hộp thư của bạn!";
    }

}
