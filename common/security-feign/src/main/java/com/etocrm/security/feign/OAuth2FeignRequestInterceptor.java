package com.etocrm.security.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@NoArgsConstructor
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
    private static final String BEARER_TOKEN_TYPE = "bearer";

    /**
     * Apply.
     *
     * @param template the template
     */
    @Override
    public void apply(RequestTemplate template) {
        //todo 令牌失效没测试
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String token = "";
        if(authentication!=null) {
            KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal) authentication.getPrincipal();
            KeycloakSecurityContext context = keycloakPrincipal.getKeycloakSecurityContext();
             token= context.getTokenString();
        }
        template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER_TOKEN_TYPE, token));
    }
}