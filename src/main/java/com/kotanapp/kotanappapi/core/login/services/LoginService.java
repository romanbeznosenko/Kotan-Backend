package com.kotanapp.kotanappapi.core.login.services;


import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.login.management.AccountBlockedException;
import com.kotanapp.kotanappapi.core.login.management.AccountNotActivatedException;
import com.kotanapp.kotanappapi.core.login.management.IncorrectLoginCredentialsException;
import com.kotanapp.kotanappapi.core.login.models.LoginRequest;
import com.kotanapp.kotanappapi.core.login.models.LoginResponse;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import com.kotanapp.kotanappapi.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final AuthAccountMapper authAccountMapper;
    private final AuthAccountManager authAccountManager;
    private final UserManager userManager;
    private final JwtService jwtService;

    public LoginResponse loginUser(LoginRequest request)
            throws UserNotFoundException, BadCredentialsException,
                   IncorrectLoginCredentialsException, AccountNotActivatedException,
                   AccountBlockedException {

        log.info("Authorizing user: {}", request.email());

        // Validate and find user
        String trimmedEmail = request.email().toLowerCase().strip();
        String trimmedPassword = request.password().strip();

        AuthAccountDAO authAccountDAO = authAccountManager
                .findByEmailAndAuthTypeAndIsActivatedTrue(trimmedEmail, AuthTypeEnum.EMAIL)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        AuthAccount authAccount = authAccountMapper.mapToDomain(
                authAccountDAO, new CycleAvoidingMappingContext()
        );

        // Check activation status
        if (!authAccount.getIsActivated()) {
            throw new AccountNotActivatedException("User not activated!");
        }

        // Verify password
        if (!passwordEncoder.matches(trimmedPassword, authAccount.getPassword())) {
            throw new IncorrectLoginCredentialsException("Incorrect login credentials!");
        }

        // Check if user is blocked
        UserDAO user = userManager.findUserById(authAccount.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (user.getBlocked() != null && user.getBlocked()) {
            throw new AccountBlockedException("Account is blocked!");
        }

        if (user.getIsArchived() != null && user.getIsArchived()) {
            throw new AccountBlockedException("Account is archived!");
        }

        log.info("Authorized user: {}", request.email());

        // Generate tokens
        boolean staySignedIn = request.staySignedIn() != null && request.staySignedIn();

        String accessToken = jwtService.generateAccessToken(
                user.getId(),
                authAccount.getAuthAccountId().getId(),
                user.getEmail(),
                user.getUserType(),
                staySignedIn
        );

        String refreshToken = jwtService.generateRefreshToken(
                user.getId(),
                authAccount.getAuthAccountId().getId(),
                user.getEmail(),
                user.getUserType(),
                staySignedIn
        );

        if (staySignedIn) {
            log.info("Extended token validity for user: {}", authAccount.getAuthAccountId().getId());
        }

        return LoginResponse.builder()
                .jwt(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

