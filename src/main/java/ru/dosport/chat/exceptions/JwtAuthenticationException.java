package ru.dosport.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ошибка: jwt токен не прошел валидацию.
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
