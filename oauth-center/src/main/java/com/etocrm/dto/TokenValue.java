package com.etocrm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:42 PM
 **/
@Data
public class TokenValue {

    @JsonProperty("access_token")
    private String accessToken;

   @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_expires_in")
    private int refreshExpiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("session_state")
    private String sessionState;
}
