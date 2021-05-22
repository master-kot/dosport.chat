package ru.dosport.main.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static ru.dosport.main.helpers.Messages.DATA_NOT_BLANK;

/**
 * Запрос для регистрации нового Пользователя
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Запрос для регистрации нового Пользователя")
public class RawUserRequest {

    //    @Size(min = 4, max = 50, message = INVALID_USERNAME_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Никнейм")
    @ApiModelProperty(notes = "Никнейм, от 4 до 50 символов",
            dataType = "String", example = "Nickname", required = true, position = 0)
    private String username;

    //    @Size(min = 6, max = 25, message = INVALID_PASSWORD_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Пароль")
    @ApiModelProperty(notes = "Пароль пользователя, от 6 до 25 символов",
            dataType = "String", required = true, position = 1)
    private String password;

    //    @Size(min = 6, max = 25, message = INVALID_PASSWORD_CONFIRM_LENGTH)
    @NotBlank(message = DATA_NOT_BLANK + "Подтверждение пароля")
    @ApiModelProperty(notes = "Подтверждение пароля, от 6 до 25 символов",
            dataType = "String", required = true, position = 2)
    private String passwordConfirm;
}
