package com.etocrm.oauth.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:28 PM
 **/
@Configuration
public class CurrentUserProvider {

    @SuppressWarnings("unchecked")
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST)
    public CurrentUser getCurrentUser() {

        CurrentUser result = new CurrentUser();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();

        } else {
            username = principal.toString();
        }

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        result.setUsername(username);
        result.setRoles(authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return result;
    }
}
