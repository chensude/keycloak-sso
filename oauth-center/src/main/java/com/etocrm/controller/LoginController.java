package com.etocrm.controller;


import com.etocrm.dto.TokenValue;
import com.etocrm.rest.UacFeignClient;
import com.etocrm.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


@RestController
public class LoginController {

    private static final int TOKEN_EXPIRATION_BUFFER_SECONDS = 15;
    private static final String BEARER_ACCESS_TOKEN_PREFIX = "Bearer ";
    private final AuthenticationService authenticationService;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private UacFeignClient uacFeignClient;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @GetMapping("/hi")
    public String testFeignClient() {
        return uacFeignClient.greeting();
    }

    @GetMapping("/login")
    public String login() {
        return "success";
    }

    @PostMapping(value = "/refresh/token")
    public ResponseEntity<TokenValue> refresh(String refreshToken) {

        return new ResponseEntity<>(authenticationService.refresh(refreshToken), HttpStatus.OK);
    }

    private boolean hasTokenExpired(final OAuth2AccessToken accessToken) {
        if (Objects.isNull(accessToken.getExpiresAt())) {
            return true;
        }

        return accessToken.getExpiresAt().isBefore(Instant.now().minus(TOKEN_EXPIRATION_BUFFER_SECONDS, ChronoUnit.SECONDS));
    }


}
