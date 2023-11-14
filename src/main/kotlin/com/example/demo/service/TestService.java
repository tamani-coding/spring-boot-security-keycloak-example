package com.example.demo.service;

import com.example.demo.mapper.CustomJwt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @PreAuthorize("hasAuthority('SCOPE_email') and hasAuthority('ROLE_default-roles-test')")
    public String test() {
        CustomJwt authentication = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getUsername();
        return "Hello " + currentPrincipalName + "!";
    }

}
