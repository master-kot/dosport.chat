package ru.dosport.main.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Jwt обертка сущности Роль пользователя.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRole implements GrantedAuthority {

    private String authority;
}
