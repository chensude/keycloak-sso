package com.example.ssoclient1.service;

import lombok.Data;

import java.util.List;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:27 PM
 **/
@Data
public class CurrentUser {

    private String username;
    private List<String> roles;
}
