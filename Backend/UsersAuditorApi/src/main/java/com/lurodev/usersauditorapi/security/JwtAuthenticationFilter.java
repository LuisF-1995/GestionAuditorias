package com.lurodev.usersauditorapi.security;

import com.lurodev.usersauditorapi.multitenancy.context.TenantContext;
import com.lurodev.usersauditorapi.multitenancy.resolver.HttpHeaderTenantResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Value("${multitenancy.http.header-name}")
    String tenantIdHeaderName;
    @Value("${server.servlet.context-path}")
    String apiBasePath;
    private final HttpHeaderTenantResolver tenantResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String requestTenantId = tenantResolver.resolveTenantIdentifier(request);
        TenantContext.setTenantId(requestTenantId);
        jwtService.setRequestTenantId(requestTenantId);
        final String authMethod = "Bearer ";
        final String jwt;
        final String email;


        if(authHeader == null || !authHeader.startsWith(authMethod)){
            filterChain.doFilter(request, response);
            return;
        }

        if(requestTenantId == null){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(tenantIdHeaderName + " value is missing from parameters");
            return;
        }

        jwt = authHeader.substring(authMethod.length());
        email = jwtService.extractUsername(jwt);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = null;
            try {
                userDetails = this.userDetailsService.loadUserByUsername(email);
            }
            catch (UsernameNotFoundException userNotFound){
                response.setStatus(HttpStatus.NOT_FOUND.value());
                response.getWriter().write("User not found");
                return;
            }

            if(userDetails != null && jwtService.isValidToken(jwt, userDetails, requestTenantId)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            else{
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("User not allowed");
                return;
            }
        }

        try {
            filterChain.doFilter(request, response);
        }finally {
            TenantContext.clear();
        }
    }
}
