package ru.dosport.main.helpers;

import static ru.dosport.main.helpers.Patterns.LOCAL_DATE_TIME_PATTERN;

/**
 * Класс, содержащий название и описание параметров для Swagger
 */
public final class SwaggerMessages {

    private SwaggerMessages () {}

    // Пользователи
    public static final String PAR_PAGE_NUMBER = "Номер страницы результатов поиска. По умолчанию - 0";
    public static final String PAR_USER_DTO = "Данные профиля пользователя";
    public static final String PAR_USER_ID = "Идентификатор пользователя";
    public static final String PAR_USERNAME = "Никнейм пользователя";
    public static final String PAR_USER_PHOTO = "Ссылка на файл фото";

    // Мероприятия
    public static final String PAR_EVENT_DTO = "Данные мероприятия";
    public static final String PAR_EVENT_ID = "Идентификатор мероприятия";
    public static final String PAR_CREATION_DATE = "Дата создания";
    public static final String PAR_CREATION_DATE_FORMAT = "Дата создания в формате " + LOCAL_DATE_TIME_PATTERN;
    public static final String PAR_START_DATE = "Дата и время начала";
    public static final String PAR_START_DATE_FORMAT = "Дата и время начала в формате " + LOCAL_DATE_TIME_PATTERN;
    public static final String PAR_END_DATE = "Дата и время окончания";
    public static final String PAR_END_DATE_FORMAT = "Дата и время окончания в формате " + LOCAL_DATE_TIME_PATTERN;
    public static final String PAR_PRIVATE = "Приватность (закрытость)";
    public static final String PAR_ORGANIZER_ID = "Идентификатор пользователя-организатора";
    public static final String PAR_ORGANIZER_DTO = "Данные пользователя-организатора";
    public static final String PAR_PRICE = "Цена участия";
    public static final String PAR_PARTICIPANTS = "Список участников";
    public static final String PAR_EVENT_DESCRIPTION = "Описание мероприятия, до 150 символов";
    public static final String PAR_MAX_USERS = "Максимальное количество участников";
    public static final String PAR_USERS_COUNT = "Текущее количество участников";
    public static final String PAR_MESSAGES_COUNT = "Текущее количество сообщений";
    public static final String PAR_CREATION_DATE_FROM =
            "Начальная дата и время интервала создания в формате " + LOCAL_DATE_TIME_PATTERN;
    public static final String PAR_CREATION_DATE_TO =
            "Конечная дата и время интервала создания в формате " + LOCAL_DATE_TIME_PATTERN;
    public static final String PAR_PRICE_MIN = "Минимальная цена";
    public static final String PAR_PRICE_MAX = "Максимальная цена";

    // Сообщения
    public static final String PAR_MESSAGE_ID = "Идентификатор сообщения";
    public static final String PAR_MESSAGE_DTO = "Данные сообщения";
    public static final String PAR_MESSAGE_TEXT = "Текст оообщения";
    public static final String PAR_MESSAGE_SENDER = "Идентификатор отправителя оообщения";
    public static final String PAR_MESSAGE_RECIPIENT = "Идентификатор получателя соообщения";

    // Виды спорта
    public static final String PAR_SPORT_TYPE_ID = "Идентификатор вида спорта";
    public static final String PAR_SPORT_TYPE_NAME = "Название вида спорта";
    public static final String PAR_SPORT_TYPE_DTO = "Данные вида спорта";
    public static final String PAR_SPORT_TYPE_LIST = "Список данных видов спорта";

    // Спортивные площадки
    public static final String PAR_SPORTGROUND_ID = "Идентификатор спортивной площадки";
    public static final String PAR_SPORTGROUND_CITY = "Город, в котором расположена площадка, до 100 символов";
    public static final String PAR_SPORTGROUND_ADDRESS = "Адрес площадки, до 255 символов";
    public static final String PAR_SPORTGROUND_NAME = "Название Площадки, до 150 символов";
    public static final String PAR_SPORTGROUND_LATITUDE = "Широта";
    public static final String PAR_SPORTGROUND_LATITUDE_MAX = "Максимальная широта зоны поиска";
    public static final String PAR_SPORTGROUND_LATITUDE_MIN = "Минимальная широта зоны поиска";
    public static final String  PAR_SPORTGROUND_LONGITUDE = "Долгота";
    public static final String  PAR_SPORTGROUND_LONGITUDE_MAX = "Максимальная долгота зоны поиска";
    public static final String  PAR_SPORTGROUND_LONGITUDE_MIN = "Минимальная долгота зоны поиска";
    public static final String  PAR_SPORTGROUND_METRO = "Станция метро, варианты: Не выбрано";
    public static final String  PAR_SPORTGROUND_SUBSCRIBERS = "Список подписчиков площадки";
    public static final String  PAR_SPORTGROUND_REVIEWS = "Список отзывов о площадке";
    public static final String  PAR_SPORTGROUND_EVENTS = "Список мероприятий на площадке";
    public static final String  PAR_SPORTGROUND_OPENING_TIME = "Время начала работы площадки";
    public static final String  PAR_SPORTGROUND_CLOSING_TIME = "Время окончания работы площадки";
    public static final String  PAR_SPORTGROUND_INFRASTRUCTURES = "Список инфраструктуры площадки, варианты: Не выбрано, " +
            "Раздевалка, Парковка, Трибуны, Душ, Освещение, Камера хранения, Аренда оборудования";
    public static final String  PAR_SPORTGROUND_OPENED = "Является ли площадка открытой (расположена на улице)";
    public static final String  PAR_SPORTGROUND_SURFACE = "Тип покрытия, варианты: Не выбрано, Грунт, Песок, Асфальт, Резина, " +
            "Искуственный газон, Натуральный газон, Паркет, Лед, Хард, Маты, Бассейн";
    public static final String PAR_SPORTGROUND_DTO = "Данные спортивной площадки";

    // Отзывы
    public static final String  PAR_REVIEWS_ID = "Идентификатор отзыва";
    public static final String PAR_REVIEWS_DTO = "Данные отзыва";

    public static final String PAR__ID = "";
}
