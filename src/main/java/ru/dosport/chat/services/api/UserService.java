package ru.dosport.chat.services.api;

import ru.dosport.chat.dto.UserDto;
import ru.dosport.chat.dto.UserRequest;
import ru.dosport.chat.entities.User;
import ru.dosport.chat.security.JwtUser;

import java.util.List;

/**
 * Сервис пользователей
 */
public interface UserService {

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * User getById(Long id) найти объект по параметру
     * UserDto getDtoById(Long id) найти Dto объект по параметру
     * List<User> getAll() найти все объекты
     * List<UserDto> getAllDto() найти все Dto объекты
     * List<UserDto> getAllDtoByObject(ObjectDto objectDto) найти все Dto объекты по параметру
     * UserDto update(UserDto userDto) изменить объект
     * UserDto save(UserDto userDto) сохранить объект
     * List<UserDto> saveAll(List<UserDto> userDtoList) сохранить список объектов
     * void delete(UserDto userDto) удалить конкретный объект
     * void deleteById(Long id) удалить объект по параметру
     * void deleteAll(List<UserDto> userDtoList) удалить список объектов
     */

    /**
     * Найти пользователя по его идентификатору
     *
     * @param id идентификатор пользователя
     * @return пользователь
     */
    UserDto getDtoById(Long id);

    /**
     * Найти пользователя по логину
     *
     * @param username логин пользователя
     * @return пользователь
     */
    UserDto getDtoByUsername(String username);

    /**
     * Получить Jwt Пользователя для генерации токенов
     *
     * @param username логин пользователя
     * @return общая сущность Пользователя
     */
    JwtUser getJwtByUsername(String username);

    /**
     * Получить общего Пользователя для межсервисного взаимодействия
     *
     * @param username логин пользователя
     * @return общая сущность Пользователя
     */
    User getByUsername(String username);

    /**
     * Найти всех пользователей
     *
     * @return список пользователей
     */
    List<UserDto> getAllDto();

    /**
     * Создать нового пользователя
     *
     * @param userRequest отображение данных пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    UserDto save(UserRequest userRequest);

    /**
     * Изменить данные пользователя по его username
     *
     * @param userDto пользователь с измененными данными
     * @param username логин пользователя
     */
    UserDto update(UserDto userDto, String username);

    /**
     * Изменить данные пользователя по его id
     *
     * @param userDto пользователь с измененными данными
     * @param id индекс пользователя
     */
    UserDto update(UserDto userDto, Long id);

    /**
     * Удалить пользователя по его идентификатору
     *
     * @param id идентификатор пользователя
     * @return удален ли пользователь
     */
    boolean deleteById(Long id);
}
