package com.example.restapi.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.NoArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;


@Service
@NoArgsConstructor
public class OtherService {

    // Resource Server( Google )에서 받은 code -> access_token -> Email추출 후, 반환 시키는 메소드
    @Transactional
    public String takeEmail(String code) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        // header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // parameter 설정
        MultiValueMap<String, String> parameters = addParameter(code);

        HttpEntity<MultiValueMap<String, String>> restRequest = new HttpEntity<>(parameters, headers);
        URI uri = URI.create("https://accounts.google.com/o/oauth2/token");
        ResponseEntity<String> restResponse = restTemplate.postForEntity(uri, restRequest, String.class);

        // parserResponseEntity 를 이용하여 token 추출 후, 반환
        String accessToken = parserResponseEntity(restResponse, "access_token");

        // 이메일 반환
        return extractEmail(accessToken);
    }

    // access_token을 받을 때, RestTemplate에 넣을 parameter 설정
    @Transactional
    public MultiValueMap<String, String> addParameter(String code) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("code", code);
        parameters.add("client_id", ""); // 추후 google.yml을 만들어서 넣을 예정
        parameters.add("client_secret", "");
        parameters.add("redirect_uri", "http://localhost:8080/api/v1/account/google/callback");
        parameters.add("grant_type", "authorization_code");

        return parameters;
    }

    // ResponseEntity<String>을 파싱해서 taget key를 갖는 value를 반환하는 메소드
    @Transactional
    public String parserResponseEntity(ResponseEntity<String> responseEntity, String taget) throws ParseException {

        /*
            compile group: 'com.googlecode.json-simple', name: 'json-simple', version: '1.1.1'

            // documents 만 뽑기
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
            JSONArray docuArray = (JSONArray) jsonObject.get("documents");
            jsonObject.get("access_token") instanceof String : True

            // 배열 첫번째 요소 뽑기
            JSONObject docuObject = (JSONObject) docuArray.get(i);
         */

        JSONParser jsonParser = new JSONParser();
        String body = responseEntity.getBody();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(body);

        return (String) jsonObject.get(taget);
    }

    // Token으로 Email을 추출하는 메소드
    @Transactional
    public String extractEmail(String accessToken) throws ParseException {

        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        // URL 설정
        URI url = URI.create("https://www.googleapis.com/oauth2/v1/userinfo");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.valueOf(url))
                .queryParam("access_token", accessToken);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // 호출
        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        return parserResponseEntity(response, "email");
    }
}