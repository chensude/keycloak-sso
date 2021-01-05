package com.etocrm.enums;

import lombok.Getter;

/**
 * @author csd
 * @desc
 * @date 1/3/21 6:37 PM
 **/
public enum GrantType {

    PASSWORD("password"),
    REFRESH("refresh_token");

    @Getter
    private String value;

    GrantType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
