package com.etocrm.request;

import lombok.Getter;
import  static com.etocrm.enums.KeycloakHeaders.*;
import static   com.etocrm.enums.GrantType.REFRESH;
/**
 * @author csd
 * @desc
 * @date 1/3/21 6:43 PM
 **/
@Getter
public class KeycloakRefreshTokenRequest extends KeycloakRequest {

    private String refreshToken;

    public KeycloakRefreshTokenRequest(String refreshToken, String clientId, String clientSecret) {
        super(REFRESH.getValue(), clientId, clientSecret);
        this.refreshToken = refreshToken;
    }

    @Override
    public String toFormUrlEncoded() {
        return super.toFormUrlEncoded() + "&" + REFRESH_TOKEN.getValue() + "=" + refreshToken;
    }
}

