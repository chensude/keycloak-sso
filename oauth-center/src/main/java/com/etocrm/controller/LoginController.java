package com.etocrm.controller;


import com.etocrm.dto.TokenValue;
import com.etocrm.rest.UacFeignClient;
import com.etocrm.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private RedisTemplate redisTemplate;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @GetMapping("/test")
    public String testUnAuthenticaiton() {
        return "test-success";
    }

    @GetMapping("/feign")
    public String testFeignClient() {
        return uacFeignClient.greeting();
    }

    @GetMapping("/test1")
    public String test1() {
        return "test1-success";
    }


    @PostMapping("/login")
    public ResponseEntity<TokenValue> login(@RequestParam("username") String username,@RequestParam("password") String password) {
        return new ResponseEntity<>(authenticationService.login(username,password), HttpStatus.OK);

    }

//    @GetMapping("/loginIn")
//    public String loginIn(@RequestParam String username,@RequestParam String password)  {
//        TokenValue login = authenticationService.login(username, password);
//    }

    @GetMapping("/logout")
    public String login(HttpServletRequest request) throws ServletException {
        request.logout();
        return "注销成功";
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
