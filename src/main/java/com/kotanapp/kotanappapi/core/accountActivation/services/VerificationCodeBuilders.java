package com.kotanapp.kotanappapi.core.accountActivation.services;

import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCode;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeId;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerificationCodeBuilders {
    public static VerificationCode buildVerificationCode(String code, Instant verificationCodeExpireAt, AuthAccount user) {

        return VerificationCode.builder()
                               .verificationCodeId(VerificationCodeId.of(null))
                               .verificationCodeExpireAt(verificationCodeExpireAt)
                               .code(code)
                               .user(user)
                               .build();
    }
}
