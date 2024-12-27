package org.example.backend.constants;

import java.time.Instant;
import java.util.UUID;

public class Constant {
    public static final String PHOTO_DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    public static final String X_REQUESTED_WITH = "X-Requested-With";

    public static final String API_CLIENT = "/";

    public static final Instant CURRENT_TIME = Instant.now();

    public static final UUID CURRENT_UUID = UUID.randomUUID();

    public static final String NHAN_VIEN = "nhanvien";

    public static final String KHACH_HANG = "khachhang";

    public static final String TRANG_THAI_USER = "Hoạt động";

}
