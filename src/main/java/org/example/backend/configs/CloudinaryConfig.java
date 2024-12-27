package org.example.backend.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = ObjectUtils.asMap(
                "cloud_name", "dlwrhv088",
                "api_key", "867273154296856",
                "api_secret", "NLFjjv3mMnGJJVc4QE5fngHOCX0",
                "secure", true // Bật chế độ bảo mật
        );
        return new Cloudinary(config);
    }
}
