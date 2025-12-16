package com.kotanapp.kotanappapi.core.accountActivation.management;

import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeDAO;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCodeDAO, UUID> {
    Optional<VerificationCodeDAO> findByCode(String code);

    Optional<VerificationCodeDAO> findByUser(AuthAccountDAO authAccountDAO);
}

