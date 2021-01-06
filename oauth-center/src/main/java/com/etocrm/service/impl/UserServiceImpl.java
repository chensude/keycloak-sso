package com.etocrm.service.impl;

import com.etocrm.domain.entity.UserEntity;
import com.etocrm.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    private static final String BEARER_TOKEN_TYPE = "bearer";
   // private final WebClient webClient;
   private final RestTemplate simpleRestTemplate;
    @Value("${auth2.keycloak.user-uri}")
    private String userUri;

    public UserServiceImpl(@Value("${auth2.keycloak.user-uri}") String userUri) {
//        webClient = WebClient.builder()
//                .baseUrl(userUri)
//                .defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
//                .build();
        simpleRestTemplate = new RestTemplateBuilder()
                .build();
    }

    @Override
    public String addUser(UserEntity userEntity) {
//        UserEntityDTO userEntityDTO = new UserEntityDTO(userEntity.getUsername(), userEntity.getCredentials(), true);
//        return  webClient.post().header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER_TOKEN_TYPE, userEntity.getToken()))
//                .bodyValue(userEntityDTO)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("username", userEntity.getUsername());
//        map.add("credentials", userEntity.getCredentials());
//        map.add("client_secret", "edc45ef0-32ca-434f-a9f9-87ca4f80ca3f");

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
