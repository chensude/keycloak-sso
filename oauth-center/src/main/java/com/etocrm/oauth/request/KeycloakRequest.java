package com.etocrm.oauth.request;

import lombok.Getter;
import static com.etocrm.oauth.enums.KeycloakHeaders.*;
import static com.etocrm.oauth.enums.KeycloakHeaders.CLIENT_SECRET;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:36 PM
 **/
@Getter
public abstract class KeycloakRequest {

    private String clientId;
    private String clientSecret;
    private String grantType;

    public KeycloakRequest(String grantType, String clientId, String clientSecret) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String toFormUrlEncoded() {
        return GRANT_TYPE.getValue() + "=" + getGrantType() + "&" +
                CLIENT_ID.getValue() + "=" + getClientId() + "&" +
                CLIENT_SECRET.getValue() + "=" + getClientSecret();
    }
}
