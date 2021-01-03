//package com.example.ssoclient1.config;
//
//import com.example.ssoclient1.provider.AuthorizeConfigManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
//    String jwkSetUri;
//    @Autowired
//    private AuthorizeConfigManager authorizeConfigManager;
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers();
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws  Exception{// @formatter:off
//
//
//        http.authorizeRequests().and()
//                .oauth2Login(Customizer.withDefaults())
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//
//        .csrf().disable();
//        authorizeConfigManager.config(http.authorizeRequests());
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
//    }
//
////    @Bean
////    public JwtAccessTokenCustomizer jwtAccessTokenCustomizer(ObjectMapper mapper) {
////        return new JwtAccessTokenCustomizer(mapper);
////    }
////
////    @Bean
////    Converter<Jwt, AbstractAuthenticationToken> grantedAuthoritiesExtractorConverter() {
////        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesExtractor());
////        return jwtAuthenticationConverter;
////    }
////
////    @Bean
////    GrantedAuthoritiesExtractor grantedAuthoritiesExtractor() {
////        return new GrantedAuthoritiesExtractor();
////    }
//
//
//}
