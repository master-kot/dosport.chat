package ru.dosport.main.helpers;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Имена ролей пользователей и методы работы с ними
 */
public final class Roles {

    private Roles() {}

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

    /**
     * Проверить, что пользователь имеет роль Админа
     * @param authentication данные авторизации
     * @return true or AccessDeniedException
     */
    public static boolean hasAuthenticationRoleAdminOrThrowException(Authentication authentication) {
        if (hasAuthenticationRoleAdmin(authentication)) return true;
        else throw new AccessDeniedException("Пользователь не является админом");
    }
}
