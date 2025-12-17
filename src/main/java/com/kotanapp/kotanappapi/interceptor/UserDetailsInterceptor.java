package com.kotanapp.kotanappapi.interceptor;

import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.utils.CustomUserDetails;
import com.kotanapp.kotanappapi.utils.DefaultResponseBuilder;
import com.kotanapp.kotanappapi.utils.UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserDetailsInterceptor implements HandlerInterceptor {
    private final UserDetailsService userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, UserNotFoundException {
        log.info("Load user details for security context");
        UserDAO user = (UserDAO) request.getAttribute("user");
        
        List<String> permissions = (List<String>) request.getAttribute("permissions");


        CustomUserDetails userDetails = userDetailsService.loadUserAndAuthority(user, permissions);

        if (userDetails == null) {
            log.info("Return 403, invalid user details");
            DefaultResponseBuilder.sendCustomResponse(response, request, 403, HttpStatus.FORBIDDEN,
                                                      "Invalid user");
            return false;
        }

        setSecurityContext(userDetails);
        return true;
    }

    private void setSecurityContext(CustomUserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext()
                             .setAuthentication(authenticationToken);
    }
}

