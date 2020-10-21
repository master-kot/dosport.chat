package ru.dosport.chat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ошибка: Сервер не нашел по данному запросов никаких ресурсов.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
