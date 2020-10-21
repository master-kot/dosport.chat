package ru.dosport.chat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static ru.dosport.chat.helpers.Messages.*;

/**
 * Запрос для регистрации нового Пользователя
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    @Size(min=4, max=50, message = INVALID_USERNAME_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Логин")
    private String username;

    @Size(min=4, max=20, message = INVALID_PASSWORD_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Пароль")
    private String password;

    private String firstName;
}
