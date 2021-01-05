

package com.etocrm.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//
//
//import com.etocrm.service.TokenService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//
@RequiredArgsConstructor
@Configuration
public class RestTemplateConfig {

    //  private final TokenService tokenService;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
////    public OAuth2RestTemplate oAuth2RestTemplate() {
////        final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
////        //oAuth2RestTemplate.setRequestFactory(new Netty4ClientHttpRequestFactory());
////        return oAuth2RestTemplate;
////
////    }
//
//
////    @Bean
////    OAuthTokenInterceptor oAuthTokenInterceptor() {
////        return new OAuthTokenInterceptor(tokenService);
////    }
////    @Bean
////    OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor() {
////        return new OAuth2FeignRequestInterceptor();
////    }
//}
