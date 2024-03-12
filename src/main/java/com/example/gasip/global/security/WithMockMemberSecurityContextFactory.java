package com.example.gasip.global.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockMemberSecurityContextFactory implements WithSecurityContextFactory<WithMockMember> {

    @Override
    public SecurityContext createSecurityContext(WithMockMember annotation) {
        MemberDetails memberDetails = MemberDetails.builder()
            .id(annotation.id())
            .email("test@email.com")
            .authorities(Arrays.stream(annotation.authorities())
                .map(SimpleGrantedAuthority::new)
                .toList())
            .build();
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(
            new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities())
        );
        return securityContext;
    }
}
