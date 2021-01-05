package com.etocrm.oauth.service;

import com.etocrm.oauth.dto.KeyToken;
import com.etocrm.oauth.request.KeycloakRefreshTokenRequest;
import com.etocrm.oauth.request.KeycloakTokenRequest;
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
        webClient = WebClient.builder()
                .baseUrl(tokenUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
    }

    @Override
    public KeyToken login(String username, String password) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(
                        new KeycloakTokenRequest(username, password, clientId, clientSecret).toFormUrlEncoded()
                )
                .retrieve()
                .bodyToMono(KeyToken.class)
                .block();
    }

    @Override
    public KeyToken refresh(String refreshToken) {
        return webClient.post()
                .bodyValue(new KeycloakRefreshTokenRequest(refreshToken, clientId, clientSecret)
                        .toFormUrlEncoded())
                .retrieve()
                .bodyToMono(KeyToken.class)
                .block();
    }
}
