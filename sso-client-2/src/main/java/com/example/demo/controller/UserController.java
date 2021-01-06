package com.example.demo.controller;

import com.example.demo.domain.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用keyloack rest api 创建Keyloak用户
 * https://sha-keycloak.etocrm.net/auth/admin/realms/myrealm/users
 * 尝试得出:这种方式直接让前端调用
 * api地址参考https://www.keycloak.org/docs-api/6.0/rest-api/index.html#_response
 * 如果要做，改成resttemplate好像更好点
 */

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/add/user")
    public String addUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("csd12345");
        Map<String, String> map = new HashMap<>();
        map.put("values","123456");
        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);
        userEntity.setCredentials(list);

        return userService.addUser(userEntity);
    }
}
