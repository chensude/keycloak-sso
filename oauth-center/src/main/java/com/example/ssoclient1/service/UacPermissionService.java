package com.example.ssoclient1.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户中心权限控制service
 */
public interface UacPermissionService {

    boolean hasPermission(Authentication authentication, HttpServletRequest request);
}
