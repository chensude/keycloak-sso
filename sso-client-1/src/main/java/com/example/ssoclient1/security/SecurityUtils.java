package com.example.ssoclient1.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    private static final String AUTH_LOGIN_AFTER_URL = "/user/loginAfter/*";
    private static final String AUTH_LOGOUT_URL = "/user/logout";

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

    public static Set<String> getCurrentAuthorityUrl() {



        Set<String> path = new HashSet<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority authority : authorities) {
            String url = authority.getAuthority();
            if (StringUtils.isNotEmpty(url)) {
                path.add(url);
            }
        }
        path.add(AUTH_LOGIN_AFTER_URL);
        path.add(AUTH_LOGOUT_URL);
        return path;
    }
}
