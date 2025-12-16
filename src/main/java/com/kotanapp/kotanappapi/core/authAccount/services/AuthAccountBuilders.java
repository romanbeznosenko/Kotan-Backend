package com.kotanapp.kotanappapi.core.authAccount.services;

import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountId;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthAccountBuilders {
    public static AuthAccount buildAuthAccount(String email, String password, AuthTypeEnum authType) {
        return AuthAccount.builder()
                          .authAccountId(AuthAccountId.of(null))
                          .isActivated(false)
                          .email(email)
                          .password(password)
                          .authType(authType)
                          .build();
    }
}
