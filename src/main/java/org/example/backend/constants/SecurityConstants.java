package org.example.backend.constants;

public class SecurityConstants {
    public static final String SECRET_KEY = "YourSecretKeyForJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/register";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds
}
