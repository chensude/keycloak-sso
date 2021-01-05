package com.etocrm.rest;

import com.etocrm.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "sso-client-two",configuration = OAuth2FeignAutoConfiguration.class)
public interface UacFeignClient {

    @GetMapping("/api/hi")
    public String  greeting() ;

}
