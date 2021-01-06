package com.etocrm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class UserEntityDTO {

    private String username;

    private List<Map<String,String>> credentials;

    private boolean enabled;
}
