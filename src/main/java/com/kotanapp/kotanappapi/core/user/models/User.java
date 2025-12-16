package com.kotanapp.kotanappapi.core.user.models;

import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UserId userId;
    private String email;
    private String name;
    private String surname;
    private Boolean blocked;
    private String avatar;
    private String language;
    private UserTypeEnum userType;

    private Boolean isArchived;
    private Instant archivedAt;

    private Instant createdAt;
    private Instant updatedAt;
}





