package com.etocrm.security.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuth2FeignAutoConfiguration {

    /**
     * Oauth 2 feign request interceptor request interceptor.
     *
     *
     * @return the request interceptor
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor();
    }

    /**
     * Feign logger level logger . level.
     *
     * @return the logger . level
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new Oauth2FeignErrorInterceptor();
    }
}