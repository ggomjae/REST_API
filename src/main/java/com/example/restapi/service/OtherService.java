package com.example.restapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;

@Service
public class OtherService {

    @Transactional
    public void googleSignCallback(String code) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", code);
        parameters.add("client_id", "");
        parameters.add("client_secret", "");
        parameters.add("redirect_uri", "http://localhost:8082/v1/api/account/googlesigncallback");
        parameters.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> rest_request = new HttpEntity<>(parameters, headers);

        URI uri = URI.create("https://accounts.google.com/o/oauth2/token");
        ResponseEntity<String> rest_response = restTemplate.postForEntity(uri, rest_request, String.class);
        String bodys = rest_response.getBody();
        System.out.println(bodys);

        //response.sendRedirect("http://localhost:8080/thkomeet");

        return;
    }
}