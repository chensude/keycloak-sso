package com.etocrm.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {
    private static final String AUTH_LOGIN_BEFORE_URL = "/login";
    private static final String AUTH_LOGIN_AFTER_URL = "/user/loginAfter/*";
    private static final String AUTH_LOGOUT_URL = "/user/logout";
    private static final String ADD_USER_URL = "/add/user";

    /**
     * Gets current login name.
     *
     * @return the current login name
     */
    public static String getCurrentLoginName() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof DefaultOAuth2User) {

            return ((DefaultOAuth2User) principal).getName();

        }

        if (principal instanceof Principal) {

            return ((Principal) principal).getName();

        }

        return String.valueOf(principal);

    }

    public static Set<String> getCurrentAuthorityUrl(RedisTemplate redisTemplate) {
        Collection<? extends GrantedAuthority> authorities =null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authorities = authentication.getAuthorities();
        if(authentication.getClass().equals(KeycloakAuthenticationToken.class)) {
           authorities=( (KeycloakAuthenticationToken)authentication).getAuthorities();
        }


        Set<String> path = new HashSet<>();
    //    Set urls = redisTemplate.opsForSet().members("urls");
    //    redisTemplate.keys("urls").clear();
     //   urls.forEach(url->path.add(url.toString()));

        //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority authority : authorities) {
            String url = authority.getAuthority().replace("ROLE_","");
            if (StringUtils.isNotEmpty(url)) {
                path.add(url);
            }
        }
        path.add(AUTH_LOGIN_BEFORE_URL);
        path.add(AUTH_LOGIN_AFTER_URL);
        path.add(AUTH_LOGOUT_URL);
        path.add(ADD_USER_URL);
        return path;
    }


}
