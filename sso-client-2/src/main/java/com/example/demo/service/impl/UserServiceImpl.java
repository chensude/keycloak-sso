package com.example.demo.service.impl;


import com.example.demo.domain.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate simpleRestTemplate;
    @Value("${auth2.keycloak.user-uri}")
    private String userUri;

    public UserServiceImpl() {
        simpleRestTemplate = new RestTemplateBuilder()
                .build();
    }

    @Override
    public String addUser(UserEntity userEntity) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity request = new HttpEntity<>(userEntity, headers);
        ResponseEntity<String> res = simpleRestTemplate.exchange(
                userUri,
                HttpMethod.POST,
                request,
                String.class
        );
        return res.getBody();
    }
}
