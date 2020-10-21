package ru.dosport.chat.helpers;

/**
 * Перечисление различных констант для информационных сообщений
 */
public final class Messages {

    private Messages() {
    }

    /**
     * Информационные сообщения
     */
    public static final String SWAGGER_WAS_STARTED = "Swagger запущен";

    /**
     * Сообщения об ошибках авторизации
     */
    public static final String USER_ALREADY_EXIST = "Пользователь с логином %s уже зарегистрирован";

    public static final String USER_NOT_FOUND_BY_USERNAME = "Пользователь с логином %s не найден";

    public static final String USER_NOT_FOUND_BY_ID = "Пользователь с индексом %s не найден";

    public static final String JWT_TOKEN_NOT_VALID = "Токен авторизации неверный либо истек его срок";

    /**
     * Иные сообщения об ошибках логики и полей запросов
     */
    public static final String ACCESS_DENIED = "Доступ в данный раздел запрещен";

    public static final String BAD_REQUEST = "Введены неверные данные";

    public static final String INVALID_DATA_VALUE = "Значение поля %s неверное: %s";

    public static final String INVALID_USERNAME_LENGTH = "Поле Адрес эл. почты должно содержать от 4 до 50 символов";

    public static final String INVALID_PASSWORD_LENGTH = "Поле Пароль должно содержать от 4 до 20 символов";

    public static final String DATA_NOT_BLANK = "Должно быть не пустым поле ";

    public static final String DATA_WAS_NOT_FOUND_BY_ID = "Данные c индексом %s не найдены";

    public static final String DATA_WAS_NOT_SAVED = "Данные не сохранены по запросу ";
}
