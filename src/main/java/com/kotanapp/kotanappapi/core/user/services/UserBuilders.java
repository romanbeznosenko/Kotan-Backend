package com.kotanapp.kotanappapi.core.user.services;

import com.kotanapp.kotanappapi.core.user.models.User;
import com.kotanapp.kotanappapi.core.user.models.UserId;
import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBuilders {
    public static User buildUserFromEmail(String email, String name, String surname, UserTypeEnum type) {

        return User.builder()
                   .userId(UserId.of(null))
                   .name(name)
                   .surname(surname)
                   .email(email.strip()
                               .toLowerCase())
                   .isArchived(false)
                   .blocked(false)
                   .userType(type)
                   .build();
    }
}
