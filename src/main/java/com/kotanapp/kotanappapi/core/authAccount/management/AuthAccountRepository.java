package com.kotanapp.kotanappapi.core.authAccount.management;

import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthAccountRepository extends CrudRepository<AuthAccountDAO, UUID> {
    Optional<AuthAccountDAO> findByEmail(String email);

    Optional<AuthAccountDAO> findByUserId(UUID id);

    Optional<AuthAccountDAO> findByEmailAndAuthType(String email, AuthTypeEnum authType);
}

