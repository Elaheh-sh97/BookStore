package com.store.bookstore.config;

import com.store.bookstore.security.CustomUserDetails;
import com.store.bookstore.service.CustomeUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomeUserDetailsService customeUserDetailsService;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(CustomeUserDetailsService customeUserDetailsService, JwtService jwtService) {
        this.customeUserDetailsService = customeUserDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        String token = null;
        String email = null;
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token = jwtService.getJwtFromHeader(request);
            email = jwtService.getEmailFromJwtToken(token);
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails customUserDetails = customeUserDetailsService.loadUserByUsername(email);
            if (jwtService.validateToken(token, customUserDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);


    }
}
