package ru.dosport.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ошибка: Сервер не сохранил данные из-за внутренней ошибки.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataNotSavedException extends RuntimeException {

    public DataNotSavedException(String message) {
        super(message);
    }
}
