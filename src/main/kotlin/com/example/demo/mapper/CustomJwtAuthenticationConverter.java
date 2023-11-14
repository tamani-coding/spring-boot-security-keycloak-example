package com.example.demo.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        // Extract claims and authorities as needed
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);

        // You can also map other information from the Jwt to the custom token
        var customJwt = new CustomJwt(jwt, authorities);
        customJwt.setUsername(jwt.getClaimAsString("name"));
        return customJwt;
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        var authorities = new ArrayList<GrantedAuthority>();

        var scopes = jwt.getClaimAsString("scope");
        if (StringUtils.hasText(scopes)) {
            Arrays.asList(scopes.split(" "))
                    .forEach(scope -> authorities.add(new SimpleGrantedAuthority("SCOPE_" + scope)));
        }

        var realm_access = jwt.getClaimAsMap("realm_access");
        if (realm_access != null && realm_access.get("roles") != null) {
            var roles = realm_access.get("roles");
            if (roles instanceof List l) {
                l.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
                );
            }
        }

        // ... your logic to extract and map the claims to GrantedAuthority ...
        return authorities;
    }
}
