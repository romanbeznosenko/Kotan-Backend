package com.kotanapp.kotanappapi.core.login.services;

import com.kotanapp.kotanappapi.core.login.management.AccountBlockedException;
import com.kotanapp.kotanappapi.core.login.models.RefreshTokenRequest;
import com.kotanapp.kotanappapi.core.login.models.RefreshTokenResponse;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import com.kotanapp.kotanappapi.utils.jwt.ExpiredJwtException;
import com.kotanapp.kotanappapi.utils.jwt.InvalidJwtException;
import com.kotanapp.kotanappapi.utils.jwt.InvalidTokenTypeException;
import com.kotanapp.kotanappapi.utils.jwt.JwtService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final JwtService jwtService;
    private final UserManager userManager;

    public RefreshTokenResponse refreshTokens(RefreshTokenRequest request)
            throws InvalidJwtException, ExpiredJwtException, InvalidTokenTypeException,
            UserNotFoundException, AccountBlockedException {

        String oldRefreshToken = request.refreshToken();

        // Validate token
        if (!jwtService.validateToken(oldRefreshToken)) {
            throw new InvalidJwtException("Invalid refresh token");
        }

        // Check if expired
        if (jwtService.isTokenExpired(oldRefreshToken)) {
            throw new ExpiredJwtException("Refresh token expired");
        }

        // Ensure it's a refresh token
        if (!jwtService.isRefreshToken(oldRefreshToken)) {
            throw new InvalidTokenTypeException("Token is not a refresh token");
        }

        // Extract claims
        UUID userId = jwtService.extractUserId(oldRefreshToken);
        UUID authId = jwtService.extractAuthId(oldRefreshToken);
        String email = jwtService.extractEmail(oldRefreshToken);
        UserTypeEnum userType = jwtService.extractUserType(oldRefreshToken);

        // Verify user still exists and is active
        UserDAO user = userManager.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getBlocked() != null && user.getBlocked()) {
            throw new AccountBlockedException("Account is blocked");
        }

        if (user.getIsArchived() != null && user.getIsArchived()) {
            throw new AccountBlockedException("Account is archived");
        }

        // Determine if original token had "stay signed in"
        // Check expiration time to infer this
        Claims claims = jwtService.extractClaims(oldRefreshToken);
        long expirationTime = claims.getExpiration().getTime();
        long issuedTime = claims.getIssuedAt().getTime();
        long tokenValidity = expirationTime - issuedTime;
        boolean wasStaySignedIn = tokenValidity > (10L * 24 * 60 * 60 * 1000L); // More than 10 days

        // Generate new tokens with same settings
        String newAccessToken = jwtService.generateAccessToken(
                userId, authId, email, userType, wasStaySignedIn
        );

        String newRefreshToken = jwtService.generateRefreshToken(
                userId, authId, email, userType, wasStaySignedIn
        );

        log.info("Refreshed tokens for user: {}", userId);

        return RefreshTokenResponse.builder()
                .jwt(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
