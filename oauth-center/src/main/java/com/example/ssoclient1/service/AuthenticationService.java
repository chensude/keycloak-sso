package com.example.ssoclient1.service;

import com.example.ssoclient1.dto.KeyToken;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:42 PM
 **/
public interface AuthenticationService {

    KeyToken login(String username, String password);

    KeyToken refresh(String refreshToken);
}