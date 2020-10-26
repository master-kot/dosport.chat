package ru.dosport.chat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

import static ru.dosport.chat.helpers.Messages.DATA_NOT_BLANK;

/**
 * Запрос для регистрации нового Пользователя
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

    private Long id;

    @NotBlank(message = DATA_NOT_BLANK + "Логин")
    private String username;

    private String firstName;

    private List<String> authorities;
}
