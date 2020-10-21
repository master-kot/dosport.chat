package ru.dosport.chat.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Класс, хранящий имена ролей пользователей
 */
public final class Roles {

    private Roles() {
    }

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * Проверить, что пользователь имеет роль Админа
     */
    public static boolean hasAuthenticationRoleAdmin(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_ADMIN)));
    }

    /**
     * Проверить, что пользователь имеет роль Пользователь
     */
    public static boolean hasAuthenticationRoleUser(Authentication authentication) {
        return (authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).anyMatch(a -> a.equals(ROLE_USER)));
    }
}
