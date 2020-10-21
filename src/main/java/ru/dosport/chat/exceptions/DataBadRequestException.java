package ru.dosport.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ошибка: Запрос не удалось обработать из-за ошибки входных данных.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataBadRequestException extends RuntimeException {

    public DataBadRequestException(String message) {
        super(message);
    }
}
