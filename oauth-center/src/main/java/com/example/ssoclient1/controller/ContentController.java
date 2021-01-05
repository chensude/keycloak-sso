package com.example.ssoclient1.controller;


import com.example.ssoclient1.domain.entity.UacAction;
import com.example.ssoclient1.dto.KeyToken;
import com.example.ssoclient1.service.AuthenticationService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
public class ContentController {

    private static final int TOKEN_EXPIRATION_BUFFER_SECONDS = 15;
    private static final String BEARER_ACCESS_TOKEN_PREFIX = "Bearer ";
    private final AuthenticationService authenticationService;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    public ContentController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @GetMapping("/content")
    public String getFoos(Authentication authentication) {
     //   token.getPrincipal();
//        OAuth2AuthorizedClient oAuth2AuthorizedClient = authorizedClientService.
//                loadAuthorizedClient(token.getAuthorizedClientRegistrationId(), token.getName());
//        if (oAuth2AuthorizedClient == null) {
//            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + oAuth2AuthorizedClient);
//        }
//
//        final OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();

//        if (hasTokenExpired(accessToken)) {
//
//            KeyToken refresh = authenticationService.refresh(accessToken.getTokenValue());
//            return refresh.getAccessToken();
//        }

        return BEARER_ACCESS_TOKEN_PREFIX;
    }

    @PostMapping(value = "/oauth2/token")
    public ResponseEntity<KeyToken> login(@RequestParam("username")  String username,
                                          @RequestParam("password")  String password) {

        return new ResponseEntity<>(authenticationService.login(username, password), HttpStatus.OK);
    }
    private boolean hasTokenExpired(final OAuth2AccessToken accessToken) {
        if (Objects.isNull(accessToken.getExpiresAt())) {
            return true;
        }

        return accessToken.getExpiresAt().isBefore(Instant.now().minus(TOKEN_EXPIRATION_BUFFER_SECONDS, ChronoUnit.SECONDS));
    }


}
