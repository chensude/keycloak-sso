package com.etocrm.config;

import com.etocrm.domain.entity.UacAction;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author csd
 * @desc
 * @date 1/3/21 11:49 PM
 **/

@Slf4j
@Component

public class SecurityAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;

//todo
    @Autowired
    private RedisTemplate redisTemplate;
    private GrantedAuthoritiesMapper grantedAuthoritiesMapper;


    public void setGrantedAuthoritiesMapper(GrantedAuthoritiesMapper grantedAuthoritiesMapper) {
        this.grantedAuthoritiesMapper = grantedAuthoritiesMapper;
    }

    /**
     * 验证Authentication，建立系统使用者信息principal(token)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws RuntimeException {

        //从token中获取用户信息
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) authentication;
        AccessToken accessToken = getAccessToken((token));
        String userId = accessToken.getSubject();
        //查询用户是否存在，若不存在则存入数据库
        // userService.checkUser(accessToken, userId);
        //根据userId查询本系统用户权限，放入token中
        //    List<GrantedAuthority> grantedAuthorities = roleService.getGrantedAuthorities(userId);
        List<UacAction> ownAuthList = new ArrayList<>();
        //UacAction uacAction1 = new UacAction("/test1", "2", "23", "321", "321");
        UacAction uacAction4 = new UacAction("/feign", "2", "23", "321", "321");

        UacAction uacAction2 = new UacAction("/hi", "2", "23", "321", "321");
        UacAction uacAction3 = new UacAction("/login", "2", "23", "321", "321");
        //ownAuthList.add(uacAction1);
        ownAuthList.add(uacAction2);
        ownAuthList.add(uacAction3);
        ownAuthList.add(uacAction4);
        List<GrantedAuthority> authList = new ArrayList<>();
        for(UacAction uacAction: ownAuthList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(uacAction.getUrl());
            authList.add(simpleGrantedAuthority);
        }

        KeycloakAuthenticationToken authenticationToken =
                new KeycloakAuthenticationToken(token.getAccount(),
                        token.isInteractive(), mapAuthorities(authList));

      //  redisTemplate.opsForSet().add("urls","/test","/login");
        return authenticationToken;
    }

    private AccessToken getAccessToken(KeycloakAuthenticationToken principal) {
        KeycloakAuthenticationToken token = principal;
        KeycloakPrincipal keycloakPrincipal = (KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext context = keycloakPrincipal.getKeycloakSecurityContext();
        return context.getToken();
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        return grantedAuthoritiesMapper != null
                ? grantedAuthoritiesMapper.mapAuthorities(authorities)
                : authorities;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return KeycloakAuthenticationToken.class.isAssignableFrom(aClass);
    }

}

