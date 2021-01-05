package com.etocrm.request;

import lombok.Getter;
import static com.etocrm.enums.KeycloakHeaders.*;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:40 PM
 **/
@Getter
public class KeycloakTokenRequest extends KeycloakRequest {

    private String username;
    private String password;

    public KeycloakTokenRequest(String username, String password, String clientId,
                                String clientSecret) {
        super(PASSWORD.getValue(), clientId, clientSecret);
        this.username = username;
        this.password = password;
    }

    @Override
    public String toFormUrlEncoded() {
        return super.toFormUrlEncoded() + "&" + USERNAME.getValue() + "=" + username + "&" + PASSWORD
                .getValue() + "=" + password;
    }
}
