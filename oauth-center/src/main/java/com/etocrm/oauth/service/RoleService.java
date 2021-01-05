package com.etocrm.oauth.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author csd
 * @desc
 * @date 1/3/21 2:33 PM
 **/
public interface RoleService {
    List<GrantedAuthority> getGrantedAuthorities(String userId);
}
