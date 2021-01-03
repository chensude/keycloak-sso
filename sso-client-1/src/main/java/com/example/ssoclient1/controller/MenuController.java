package com.example.ssoclient1.controller;

import com.example.ssoclient1.dto.MenuDto;
import com.example.ssoclient1.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author csd
 * @desc
 * @date 1/3/21 1:31 PM
 **/
@RestController
@RequestMapping(path = "/iam/menus", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuDto> findMenu() {
        return menuService.findMenu();
    }
}
