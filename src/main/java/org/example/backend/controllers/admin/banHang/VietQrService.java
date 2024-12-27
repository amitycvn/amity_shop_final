package org.example.backend.controllers.admin.banHang;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class VietQrService {

    private final RestTemplate restTemplate;

    public VietQrService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateQrCode(String accountNo, String accountName, String acqId, String addInfo, double amount) {
        String url = "https://api.vietqr.io/v2/generate";

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-client-id", "c5c02abc-f0ce-4fa0-9dea-6292db8eded7");
        headers.set("x-api-key", "d80d9a2e-76c2-4efc-b64a-a99d46b4b37d");
        headers.set("Content-Type", "application/json");

        // Body
        Map<String, Object> body = new HashMap<>();
        body.put("accountNo", accountNo);
        body.put("accountName", accountName);
        body.put("acqId", acqId);
        body.put("addInfo", addInfo);
        body.put("amount", amount);
        body.put("template", "compact");

        // Request Entity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Call VietQR API
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return response.getBody();
    }
}