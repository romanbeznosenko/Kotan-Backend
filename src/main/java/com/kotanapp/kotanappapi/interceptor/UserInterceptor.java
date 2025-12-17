package com.kotanapp.kotanappapi.interceptor;

import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.utils.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {
    private final UserManager userManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("UserInterceptor, handle user");
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                                                                                  .getAuthentication()
                                                                                  .getPrincipal();

        UUID userId = userDetails.getUser().getId();
        UserDAO user = userManager.findUserById(userId)
                                  .orElse(null);

        request.setAttribute("user", user);
        return true;
    }
}
