package com.etocrm.service.impl;

import com.etocrm.dto.TokenValue;
import com.etocrm.request.KeycloakRefreshTokenRequest;
import com.etocrm.request.KeycloakTokenRequest;
import com.etocrm.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:43 PM
 **/
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final WebClient webClient;

    @Value("${auth2.keycloak.client-id}")
    private String clientId;

    @Value("${auth2.keycloak.client-secret}")
    private String clientSecret;

    public AuthenticationServiceImpl(@Value("${auth2.keycloak.token-uri}") String tokenUri) {
        String mediaType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        webClient = WebClient.builder()
                .baseUrl(tokenUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, mediaType)
                .build();
    }

    @Override
    public TokenValue login(String username, String password) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(
                        new KeycloakTokenRequest(username, password, clientId, clientSecret).toFormUrlEncoded()
                )
                .retrieve()
                .bodyToMono(TokenValue.class)
                .block();
    }

    @Override
    public TokenValue refresh(String refreshToken) {
        return webClient.post()
                .bodyValue(new KeycloakRefreshTokenRequest(refreshToken, clientId, clientSecret)
                        .toFormUrlEncoded())
                .retrieve()
                .bodyToMono(TokenValue.class)
                .block();
    }
}
