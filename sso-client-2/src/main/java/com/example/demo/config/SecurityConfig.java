package com.example.demo.config;

//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
//    String jwkSetUri;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
//        http.authorizeRequests(req -> req
//                .antMatchers("/", "/login**")
//                .permitAll().anyRequest().authenticated())
//            .oauth2Login(Customizer.withDefaults())
//            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//        ;
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
//    }
//}
