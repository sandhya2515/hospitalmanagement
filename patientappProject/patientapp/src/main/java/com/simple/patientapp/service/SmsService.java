package com.simple.patientapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SmsService {

    private static final String SMS_API_URL = "https://api.myconnectlounge.in/fe/api/v1/multiSend";
    private static final String USERNAME = "medhlp.api";
    private static final String PASSWORD = "pNGeELQ~";
    private static final String FROM = "Medhlp";
   // private static final String To = "8668764053";

    public void sendSms(String phoneNumber, String message) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(SMS_API_URL)
                    .queryParam("username", USERNAME)
                    .queryParam("password", PASSWORD)
                    .queryParam("unicode", false)
                    .queryParam("from", FROM)
                    .queryParam("to", phoneNumber)
                    .queryParam("text", message)
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();
            System.out.println("KautubhURL"+url);
            String response = restTemplate.getForObject(url, String.class);
            
            System.out.println(message);
            System.out.println("SMS API Response: " + response);
        } 
        catch (Exception e) {
            System.err.println("Error sending SMS: " + e.getMessage());
        }
    }
}
