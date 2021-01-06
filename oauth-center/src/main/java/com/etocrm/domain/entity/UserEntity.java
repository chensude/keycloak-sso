package com.etocrm.domain.entity;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserEntity {

    private String username;

    private List<Map<String,String>> credentials;

    private boolean enabled;

    private String token;

}
