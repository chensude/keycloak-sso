package com.etocrm.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.stereotype.Component;

/**
 * springSecurity核心模块的授权配置提供起，安全模块设计的url授权配置都在这里配置
 * 配置Order为了先读到这里的配置
 */
@Component
@Order(Integer.MIN_VALUE)
public class NcAuthorizeConfigProvider implements AuthorizeConfigProvider {


    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;

    public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
    }

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/actuator/**","/druid/**", "/auth/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs",
                "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html","/webjars/**","/","/oauth2/**").permitAll();

        return false;
    }
}
