package com.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URLEncoder;

@Service
public class SMSService {

    public String sendSMSViaPhone(String username, String password, String address, String port, String phone, String message) throws IOException {
        String messageEncoded = URLEncoder.encode(message, "UTF-8");

        return WebClient
                .builder()
                .baseUrl(address + ":" + port)
                .build()
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/SendSMS")
                        .queryParam("username", username)
                        .queryParam("password", password)
                        .queryParam("phone", phone)
                        .queryParam("message", messageEncoded)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
