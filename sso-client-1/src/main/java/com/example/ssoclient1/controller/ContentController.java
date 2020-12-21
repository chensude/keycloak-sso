package com.example.ssoclient1.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Lists;

import java.util.Arrays;
import java.util.List;

@RestController
public class ContentController {

    @GetMapping("/content")
    public String getFoos(Model model) {
        model.addAttribute("foos", Arrays.asList("Client-1", "This is first client"));
        return "content-client-one";
    }
}
