package com.etocrm.oauth.security;

import com.etocrm.oauth.provider.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import org.springframework.stereotype.Component;

/**
 *UAC模块的授权配置，应用授权权限表达式
 */
@Component
@Order
public class NcPermissionAuthorizeConfigProvider implements AuthorizeConfigProvider {


    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.anyRequest()
                .access("@permissionService.hasPermission(authentication,request)");
        return true;
    }

}
