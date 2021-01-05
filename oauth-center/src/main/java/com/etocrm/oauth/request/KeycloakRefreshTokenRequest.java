package com.etocrm.oauth.request;

import lombok.Getter;
import  static com.etocrm.oauth.enums.KeycloakHeaders.*;
import static   com.etocrm.oauth.enums.GrantType.REFRESH;
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

