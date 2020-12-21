package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ContentController {

    @GetMapping("/content")
    public String getFoos(Model model) {
        model.addAttribute("foos", Arrays.asList("Client-2", "This is second client"));
        return "content-client-two";
    }
}
