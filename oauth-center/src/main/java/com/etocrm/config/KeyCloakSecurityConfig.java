package com.etocrm.config;

import com.etocrm.security.AuthorizeConfigManager;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * @author csd
 * @desc
 * @date 1/3/21 11:51 PM
 **/
@KeycloakConfiguration
public class KeyCloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Autowired
    private SecurityAuthenticationProvider authenticationProvider;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

//    @Bean
//    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
//            KeycloakAuthenticationProcessingFilter filter) {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//        registrationBean.setEnabled(true);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
//            KeycloakPreAuthActionsFilter filter) {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
//        registrationBean.setEnabled(true);
//        return registrationBean;
//    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().and()
                //req -> req
//                .antMatchers("/", "/login**","/oauth2/**")
//                .permitAll().anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)

        .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }
}

