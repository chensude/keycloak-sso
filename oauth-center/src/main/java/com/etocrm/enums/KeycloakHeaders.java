package com.etocrm.enums;

import lombok.Getter;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:39 PM
 **/
public enum  KeycloakHeaders {

    GRANT_TYPE("grant_type"),
    USERNAME("username"),
    PASSWORD("password"),
    SCOPE("scope"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret"),
    REFRESH_TOKEN("refresh_token");

    @Getter
    private String value;

    KeycloakHeaders(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
