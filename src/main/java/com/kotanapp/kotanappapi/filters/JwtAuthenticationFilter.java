package com.kotanapp.kotanappapi.filters;

import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.utils.CustomUserDetails;
import com.kotanapp.kotanappapi.utils.UserDetailsService;
import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import com.kotanapp.kotanappapi.utils.jwt.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserManager userManager;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Extract Authorization header
        String authHeader = request.getHeader("Authorization");

        // Check if Bearer token is present
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);

        try {
            // Validate token
            if (!jwtService.validateToken(jwt)) {
                log.warn("Invalid JWT token");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Check if token is expired
            if (jwtService.isTokenExpired(jwt)) {
                log.warn("Expired JWT token");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Ensure it's an access token (not refresh)
            if (!jwtService.isAccessToken(jwt)) {
                log.warn("Refresh token used for API access");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Extract user ID from token
            UUID userId = jwtService.extractUserId(jwt);

            // Load user and check if blocked/deactivated
            UserDAO user = userManager.findUserById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (user.getBlocked() != null && user.getBlocked()) {
                log.warn("Blocked user attempted access: {}", userId);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if (user.getIsArchived() != null && user.getIsArchived()) {
                log.warn("Archived user attempted access: {}", userId);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // Create CustomUserDetails with authorities
            CustomUserDetails userDetails = userDetailsService.loadUserAndAuthority(user, Collections.emptyList());

            // Create Authentication token and set in SecurityContext
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            log.debug("Successfully authenticated user: {}", userId);

        } catch (JwtException | UsernameNotFoundException e) {
            log.error("Authentication error: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        // Skip filter for whitelisted paths
        String path = request.getRequestURI();
        return path.startsWith("/auth/") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/error/") ||
                path.startsWith("/ws/") ||
                path.startsWith("/internal/") ||
                path.equals("/csrf");
    }
}
