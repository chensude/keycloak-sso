package com.etocrm.service;

import com.etocrm.dto.TokenValue;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:42 PM
 **/
public interface AuthenticationService {

    TokenValue login(String username, String password);

    TokenValue refresh(String refreshToken);
}
