package com.etocrm.config;


import com.etocrm.service.OAuthTokenInterceptor;
import com.etocrm.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
public class RestTemplateConfig {

    private final TokenService tokenService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder()
            .interceptors(oAuthTokenInterceptor())
            .build();
    }
//    @Bean("nepousOAuth2RestTemplate")
//    public OAuth2RestTemplate oAuth2RestTemplate() {
//        final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
//        //oAuth2RestTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());
//        return oAuth2RestTemplate;
//
//    }


    @Bean
    OAuthTokenInterceptor oAuthTokenInterceptor() {
        return new OAuthTokenInterceptor(tokenService);
    }
}