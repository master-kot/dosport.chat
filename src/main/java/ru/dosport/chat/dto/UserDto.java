package ru.dosport.chat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static ru.dosport.chat.helpers.Messages.DATA_NOT_BLANK;
import static ru.dosport.chat.helpers.Messages.INVALID_USERNAME_LENGTH;

/**
 * Dto представление сущности Пользователь
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    @Size(min=4, max=50, message = INVALID_USERNAME_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Логин")
    private String username;

    @NotBlank(message = DATA_NOT_BLANK + "Имя")
    private String firstName;
}
