package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('SCOPE_email') and hasAuthority('ROLE_default-roles-test')")
    public String getTest() {
        return "test";
    }
}