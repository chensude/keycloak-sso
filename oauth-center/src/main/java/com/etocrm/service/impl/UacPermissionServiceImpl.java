package com.etocrm.service.impl;


import com.etocrm.security.SecurityUtils;
import com.etocrm.service.UacPermissionService;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Slf4j
@Component("permissionService")
public class UacPermissionServiceImpl implements UacPermissionService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean hasPermission(Authentication authentication, HttpServletRequest request) {
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        Set<String> currentAuthorityUrl = SecurityUtils.getCurrentAuthorityUrl(redisTemplate);
        String requestURI = request.getRequestURI();
        log.info("验证权限loginName={}, requestURI={}, hasAuthorityUrl={}", currentLoginName, requestURI, Joiner.on(",").join(currentAuthorityUrl));

        // 超级管理员 全部都可以访问
        if (StringUtils.equals(currentLoginName, "admin")) {
            return true;
        }
//        //Feign客户端具有所有权限, 如果需要则在角色权限中控制
//        if (currentLoginName.contains(OAUTH2_CLIENT_PREFIX)) {
//            return false;
//            //ClientDetails clientDetails = clientDetailsService.loadClientByClientId(currentLoginName);
//            //return clientDetails != null;
//        }

//
       for(final String authority : currentAuthorityUrl) {
            // Uac项目放过查询权限
            if (requestURI.contains("query") || requestURI.contains("get") || requestURI.contains("check") || requestURI.contains("select")) {
                return true;
            }
            if (antPathMatcher.match(authority,requestURI)) {
                return true;
            }
        }
        return false;
    }


}
