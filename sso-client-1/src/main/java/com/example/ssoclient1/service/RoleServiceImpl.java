package com.example.ssoclient1.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author csd
 * @desc
 * @date 1/3/21 2:33 PM
 **/
@Service
public class RoleServiceImpl implements RoleService {

//    @Autowired
//    private RoleRepository roleRepository;
    @Override
    public List<GrantedAuthority> getGrantedAuthorities(String userId) {
        return null;
    }
}
